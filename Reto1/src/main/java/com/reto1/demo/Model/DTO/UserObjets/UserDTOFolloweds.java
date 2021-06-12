package com.reto1.demo.Model.DTO.UserObjets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOFolloweds {
    @JsonProperty("userId")
    int id;
    @JsonProperty("userName")
    String name;
    List<UserDTO> followed;
}
