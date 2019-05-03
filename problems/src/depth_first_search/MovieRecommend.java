package depth_first_search;

import java.util.*;

/** Created by gouthamvidyapradhan on 25/02/2017. Accepted */
public class MovieRecommend {
  Set<Integer> visited = new HashSet<>();
  List<Movie> list = new ArrayList<>();

  class Movie {
    private int movieId;
    private float rating;
    private ArrayList<Movie> similarMovies;

    public List<Movie> getSimilarMovies() {
      return similarMovies;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {}

  public Set<Movie> getMovieRecommendations(Movie movie, int N) {
    dfs(movie);
    Set<Movie> result = new HashSet<>();
    Comparator<Movie> cmp =
        new Comparator<Movie>() {
          @Override
          public int compare(Movie o1, Movie o2) {
            return Float.compare(o2.rating, o1.rating);
          }
        };
    Collections.sort(list, cmp);

    if (list.size() < N) {
      result.addAll(list);
      return result;
    }

    for (int i = 0; i < N; i++) {
      result.add(list.get(i));
    }

    return result;
  }

  private void dfs(Movie m) {
    visited.add(m.movieId); // mark this visited
    List<Movie> movies = m.getSimilarMovies();
    for (Movie mo : movies) {
      if (!visited.contains(mo.movieId)) {
        list.add(mo);
        dfs(mo);
      }
    }
  }
}
