package com.reto1.demo.Model.DTO.Mapper;

import com.reto1.demo.Model.DTO.UserObjets.UserDTO;
import com.reto1.demo.Model.DTO.UserObjets.UserDTOCount;
import com.reto1.demo.Model.DTO.UserObjets.UserDTOFolloweds;
import com.reto1.demo.Model.DTO.UserObjets.UserDTOFollowers;
import com.reto1.demo.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface IUserMapper {

  @Mappings({
          @Mapping(source = "user", target = "followers_count", qualifiedByName = "followersCount")
  })
  UserDTOCount toUserCount(User user);

  @Mappings({
          @Mapping(source = "user.followers", target = "followers", qualifiedByName = "userListNotation")
  })
  UserDTOFollowers toUserFollowers(User user);

  UserDTO toUserDTO(User user);

  @Mappings({
          @Mapping(source = "user.followed", target = "followed", qualifiedByName = "userListNotation")
  })
  UserDTOFolloweds toUserFolloweds(User user);

  @Named("followersCount")
  default int locationToLocationDto(User user) {
    return user.getFollowers().size();
  }

  @Named("userListNotation")
  default List<UserDTO> toListUserDTO(List<User> listUsers){
    return listUsers.stream().map(user -> toUserDTO(user)).collect(Collectors.toList());
  }
}
