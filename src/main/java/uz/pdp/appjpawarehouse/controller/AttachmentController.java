package uz.pdp.appjpawarehouse.controller;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appjpawarehouse.entity.Attachment;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.AttachmentService;

@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {

    final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){

        Result result = attachmentService.uploadFile(request);
        return result;
    }

    @GetMapping("/get")
    public Page<Attachment> getAttachmentList(@RequestParam Integer page){

        Page<Attachment> attachmentList = attachmentService.getAttachmentList(page);
        return attachmentList;
    }

    @GetMapping(value = "/get/{attachmentId}")
    public Attachment getOneAttachment(@PathVariable Integer attachmentId){

        Attachment oneAttachment = attachmentService.getOneAttachment(attachmentId);
        return oneAttachment;
    }

    @PutMapping(value = "/edit/{attachmentId}")
    public Result editAttachment(@PathVariable Integer attachmentId, MultipartHttpServletRequest request){

        Result result = attachmentService.editAttachment(attachmentId, request);
        return result;
    }

    @DeleteMapping(value = "/delete/{attachmentId}")
    public Result deleteAttachment(@PathVariable Integer attachmentId){

        Result result = attachmentService.deleteAttachment(attachmentId);
        return result;
    }
}
