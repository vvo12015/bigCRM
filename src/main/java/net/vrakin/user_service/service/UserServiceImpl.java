package net.vrakin.user_service.service;

import net.vrakin.user_service.dto.UserRequestDTO;
import net.vrakin.user_service.entity.User;
import net.vrakin.user_service.mapper.UserMapper;
import net.vrakin.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реалізація сервісу {@link UserService}, який забезпечує бізнес-логіку для роботи з користувачами.
 *
 * <p>Цей клас використовує {@link UserRepository} для виконання операцій з базою даних.</p>
 *
 * @author Valentyn Vrakin
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserRequestDTO dto) {
        if (userRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Користувач з таким ім'ям вже існує!");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<User> entities) {
        userRepository.saveAll(entities);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public List<User> findByNameLike(String namePattern) {
        return userRepository.findByNameLike(namePattern);
    }
}
