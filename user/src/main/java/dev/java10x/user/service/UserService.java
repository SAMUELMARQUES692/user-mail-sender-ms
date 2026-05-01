package dev.java10x.user.service;

import dev.java10x.user.dto.UserDTO;
import dev.java10x.user.exception.EmailDuplicadoException;
import dev.java10x.user.mapper.UserMapper;
import dev.java10x.user.model.UserModel;
import dev.java10x.user.producer.UserProducer;
import dev.java10x.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private  final UserMapper userMapper;
    private final UserProducer userProducer;

    @Transactional
    public UserDTO saveAndSend(UserDTO userDTO) {
        validarEmailDuplicado(userDTO.email());
        UserModel saveUser = userRepository.save(userMapper.toModel(userDTO));
         userProducer.publishEvent(saveUser);
         return userMapper.toDto(saveUser);
    }

    private void validarEmailDuplicado(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new EmailDuplicadoException("O email " + email + " já está em uso.");
        });
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public void deleteUser(UUID id) {
         userRepository.deleteById(id);
    }

}
