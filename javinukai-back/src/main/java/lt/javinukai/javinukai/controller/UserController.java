package lt.javinukai.javinukai.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.javinukai.javinukai.dto.request.user.UserRegistrationRequest;
import lt.javinukai.javinukai.dto.request.user.UserUpdateRequest;
import lt.javinukai.javinukai.entity.User;
import lt.javinukai.javinukai.mapper.UserMapper;
import lt.javinukai.javinukai.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
@PreAuthorize("hasAnyRole(['ADMIN', 'MODERATOR'])")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable UUID userId) {
        log.debug("{}: Data for user {} requested", this.getClass().getName(), userId);
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRegistrationRequest registration) {
        log.debug("{}: Registering user {} {}",
                this.getClass().getName(), registration.getName(), registration.getSurname());
        return ResponseEntity.ok().body(userService.createUser(UserMapper.mapToUser(registration)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody @Valid UserUpdateRequest updateDTO) {
        return ResponseEntity.ok().body(userService.updateUser(UserMapper.mapToUser(updateDTO), userId));
    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.deleteUser(userId));
    }


}
