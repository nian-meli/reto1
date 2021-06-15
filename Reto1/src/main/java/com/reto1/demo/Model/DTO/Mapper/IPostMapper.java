package com.reto1.demo.Model.DTO.Mapper;

import com.reto1.demo.Model.DTO.PostObjects.LastPostDTO;
import com.reto1.demo.Model.DTO.PostObjects.PostDTO;
import com.reto1.demo.Model.DTO.PostObjects.PromoPostCount;
import com.reto1.demo.Model.DTO.UserObjets.UserPromoPostListDTO;
import com.reto1.demo.Model.Post;
import com.reto1.demo.Model.PromoPost;
import com.reto1.demo.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface IPostMapper {

    PostDTO toPostDTO(Post post);
    PostDTO toPromoPostDTO(PromoPost post);
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.name", target = "userName"),
            @Mapping(source = "user.posts", target = "promoproductscount", qualifiedByName = "promoPostCount")})
    PromoPostCount toPromoPostCountMapper(User user);

    @Mappings({
      @Mapping(source = "user.id", target = "userId"),
          @Mapping(source = "recentPost", target = "posts", qualifiedByName = "postDTOList")
  })
     LastPostDTO toLastPostDTO(User user, ArrayList<Post> recentPost);

    @Mapping(source = "user.posts", target = "posts", qualifiedByName = "promoPostList")
    UserPromoPostListDTO toPromoPostDTO(User user);

    @Named("postDTOList")
    default List promoPost(List<Post> posts){
        return posts.stream().map(post ->
                post instanceof PromoPost ? toPromoPostDTO((PromoPost) post)
                        : toPostDTO((Post) post)).collect(Collectors.toList());
    }

    @Named("promoPostList")
    default List promoPostList(List<Post> posts){
        return posts.stream().filter(post -> post instanceof PromoPost).
                            map(promoPost -> toPromoPostDTO((PromoPost) promoPost)).collect(Collectors.toList());
    }


    @Named("promoPostCount")
    default int promoPostCount(List<Post> posts){
        return (int) posts.stream().filter(post -> post instanceof PromoPost).count();
    }







}
