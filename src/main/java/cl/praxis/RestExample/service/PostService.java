package cl.praxis.RestExample.service;

import cl.praxis.RestExample.model.Post;

import java.util.List;

public interface PostService {

  List<Post> findAll();
  Post findOne(long id);
  Post update(long id, Post post);
  Post create(Post post);
  void delete(long id);



}

