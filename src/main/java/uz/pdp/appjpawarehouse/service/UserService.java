package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.User;
import uz.pdp.appjpawarehouse.entity.Warehouse;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.payload.UserDTO;
import uz.pdp.appjpawarehouse.repositort.UserRepository;
import uz.pdp.appjpawarehouse.repositort.WarehouseRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    final UserRepository userRepository;
    final WarehouseRepository warehouseRepository;

    public UserService(UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Result addUser(@RequestBody UserDTO userDTO) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDTO.getWarehousesID());
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id", false);

        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDTO.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result(userDTO.getPhoneNumber() + " the phone  number already exist", false);

        Integer maxUserId = userRepository.maxUserId();
        User newUser = new User();

        newUser.setFirstname(userDTO.getFirstname());
        newUser.setLastName(userDTO.getLastName());
        newUser.setPassword(userDTO.getPassword());
        newUser.setPhoneNumber(userDTO.getPhoneNumber());
        newUser.setWarehouses((Set<Warehouse>) optionalWarehouse.get());
        newUser.setCode(String.valueOf(maxUserId + 1));
        newUser.setActive(userDTO.getActive());

        User save = userRepository.save(newUser);
        return new Result("New user successfully added", true, save.getId());
    }

    public Page<User> getUserPage(Integer page) {

        Pageable pageable = PageRequest.of(page, 20);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    public Result editUser(Integer userId, UserDTO userDTO) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            return new Result("Invalid User Id", false);

        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDTO.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result(userDTO.getPhoneNumber() + " the phone  number already exist", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDTO.getWarehousesID());
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id", false);

        User user = optionalUser.get();

        user.setActive(userDTO.getActive());
        user.setFirstname(user.getFirstname());
        user.setLastName(user.getLastName());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setWarehouses((Set<Warehouse>) optionalWarehouse.get());

        userRepository.save(user);
        return new Result("User edited.", true);
    }

    public Result deleteUser(@PathVariable Integer userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            return new Result("Invalid User Id", false);

        userRepository.deleteById(userId);
        return new Result("User deleted.", true);
    }

}
