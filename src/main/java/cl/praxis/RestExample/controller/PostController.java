package cl.praxis.RestExample.controller;

import cl.praxis.RestExample.model.Post;
import cl.praxis.RestExample.service.PostService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/template/")
public class PostController {

  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  //@RequestMapping(value="/post", method = RequestMethod.GET)
  @GetMapping("/post")
  public List<Post> getPostList(){
    return service.findAll();

  }
  //@RequestMapping(value="/post", method = RequestMethod.GET)
  @GetMapping("/post/{id}")
  public Post getPostOne(
          @PathVariable("id") long id
  ){
    return service.findOne(id);
  }

  //@RequestMapping(value="/post", method = RequestMethod.POST)
  @PostMapping("/post")
  public Post createPost(@RequestBody Post post){
      return service.create(post);
  }

  @PutMapping("/post/{id}")
  public Post updatePost(
          @PathVariable("id") long id,
          @RequestBody Post post){

    return service.update(id, post);

  }

  @DeleteMapping("/post/{id}")
  public String deletePost(@PathVariable("id") long id){

    service.delete(id);

    return "{ \"status\": \"ok\", \"message\": \"borrado\" }";
  }
}
