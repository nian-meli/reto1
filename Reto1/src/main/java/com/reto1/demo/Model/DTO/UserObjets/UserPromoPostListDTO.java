package com.reto1.demo.Model.DTO.UserObjets;

import com.reto1.demo.Model.DTO.PostObjects.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPromoPostListDTO {
    int id;
    String name;
    List<PostDTO> posts;
}
