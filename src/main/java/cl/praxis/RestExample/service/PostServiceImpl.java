package cl.praxis.RestExample.service;

import cl.praxis.RestExample.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  private final RestTemplate restTemplate;

  public PostServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  @Override
  public List<Post> findAll() {

    HttpHeaders headers = new HttpHeaders();

    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String lista = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
      HttpMethod.GET, entity, String.class).getBody();

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(lista, new TypeReference<List<Post>>() {});

    } catch (Exception e) {
      e.printStackTrace();
      }

    return null;
  }

  @Override
  public Post findOne(long id) {

    HttpHeaders headers = new HttpHeaders();

    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String one = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/" + id,
            HttpMethod.GET, entity, String.class).getBody();

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(one, new TypeReference<Post>(){});

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;

  }

  @Override
  public Post update(long id, Post post) {

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Post> entity = new HttpEntity<Post>(post, headers);

    String one = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/" + id ,
            HttpMethod.PUT, entity, String.class).getBody();

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(one, new TypeReference<Post>(){});

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Post create(Post post) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Post> entity = new HttpEntity<Post>(post, headers);

    String one =  restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
            HttpMethod.POST, entity, String.class).getBody();

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(one, new TypeReference<Post>(){});

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;

  }

  @Override
  public void delete(long id) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Post> entity = new HttpEntity<Post>(headers);

    String post = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/" + id ,
            HttpMethod.DELETE, entity, String.class).getBody();

  }
}
