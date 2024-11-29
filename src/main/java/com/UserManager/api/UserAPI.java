package com.UserManager.api;


import com.UserManager.model.entites.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "USERS", description = "User Api")
@RequestMapping("/user")
public interface UserAPI {

    @Operation(summary = " Get all users!!! ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK!", content =
                    {@Content(mediaType = "application/json"
                            , array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
            })
    @PostMapping("/getAll")
    ResponseEntity<List<User>> findAll();

    //**********************************************************************************************

    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK!",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = User.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @GetMapping("/find/{id}")
    ResponseEntity<User> findById(@Validated @PathVariable(name = "id") Long userId);
    //**********************************************************************************************

    @Operation(summary = "Create new User")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201", description = "Created !",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input")
            , @ApiResponse(responseCode = "409", description = "This user already exists")})
    @PostMapping("/save")
    ResponseEntity<User> save(@Validated @RequestBody User user);

    //**********************************************************************************************

    @Operation(summary = "Update a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @PutMapping("/update/{id}")
    User update(@PathVariable("id") Long id, @Validated @RequestBody User user);

    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content - Successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found - User does not exist", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long userId);
}
