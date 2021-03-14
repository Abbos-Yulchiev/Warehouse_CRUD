package uz.pdp.appjpawarehouse.service;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appjpawarehouse.entity.Attachment;
import uz.pdp.appjpawarehouse.entity.AttachmentContent;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.AttachmentContentRepository;
import uz.pdp.appjpawarehouse.repositort.AttachmentRepository;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());

        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = new AttachmentContent();

        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new Result("File saved", true, savedAttachment.getId());
    }

    public Page<Attachment> getAttachmentList(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        Page<Attachment> attachmentPage = attachmentRepository.findAll(pageable);
        return attachmentPage;
    }

    public Attachment getOneAttachment(Integer attachmentId) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent())
            return new Attachment();
        return optionalAttachment.get();
    }

    @SneakyThrows
    public Result editAttachment(Integer attachmentId, MultipartHttpServletRequest request) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);

        if (!optionalAttachment.isPresent())
            return new Result("Invalid attachment ID", false);

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment editedAttachment = optionalAttachment.get();

        assert file != null;
        editedAttachment.setContentType(file.getContentType());
        editedAttachment.setName(file.getOriginalFilename());
        editedAttachment.setSize(file.getSize());

        Attachment savedAttachment = attachmentRepository.save(editedAttachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new Result("Attachment edited", true, savedAttachment.getId());
    }

    public Result deleteAttachment(Integer attachmentId) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent())
            return new Result("Invalid Attachment Id", false);

        attachmentRepository.deleteById(attachmentId);
        return new Result("Attachment deleted", true);

    }
}
