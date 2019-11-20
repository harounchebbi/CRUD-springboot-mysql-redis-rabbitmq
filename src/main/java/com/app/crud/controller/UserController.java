package com.app.crud.controller;

import com.app.crud.entity.User;
import com.app.crud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
@Api(tags = {"users"})
public class UserController extends AbstractRestHandler {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single user.", notes = "You have to provide a valid user ID.")
    public User findOneUser(@ApiParam(value = "The ID of the user.", required = true) @PathVariable("id") Long id) {
        log.info("User UserController {}", userService.findUserById(id));
        return userService.findUserById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all users.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Page<User> getAllUsers(@ApiParam(value = "The page number (zero-based)", required = true)
                           @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUM) Integer page,
                           @ApiParam(value = "Tha page size", required = true)
                           @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return userService.getAllUsers(page, size);
    }


    @GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a user resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createUser(@RequestBody User user,
                           HttpServletRequest request, HttpServletResponse response) {
        final User createUser = userService.saveUser(user);
        response.setHeader("Location", request.getRequestURL().append("/").append(createUser.getId()).toString());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a user resource.", notes = "You have to provide a valid user ID in the URL and in the payload. The ID attribute can not be updated.")
    public void modifyUser(@ApiParam(value = "The ID of the existing user resource.", required = true) @PathVariable("id") Long id, @RequestBody User user) {
        checkResourceFound(this.userService.findUserById(id));
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a user resource.", notes = "You have to provide a valid user ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteUser(@ApiParam(value = "The ID of the existing user resource.", required = true) @PathVariable("id") Long id) {
        checkResourceFound(this.userService.findUserById(id));
        userService.deleteUser(id);
    }




}
