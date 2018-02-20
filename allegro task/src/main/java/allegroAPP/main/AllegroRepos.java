package allegroAPP.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class AllegroRepos {
	
	
	
	public List<Repo> getReposSortedByPushedAtDate() {
		
		List<Repo> repositories = getRepos();
		repositories.sort((repo1, repo2) -> repo1.getPushedAtAsDate().compareTo(repo2.getPushedAtAsDate()));
		return repositories;
	}
	
	public List<Repo> getRepos() {
		String json = null;
		try {
			json = getBody("https://api.github.com/users/allegro/repos?per_page=1000&page=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();

		Repo[] repos = gson.fromJson(json, Repo[].class);

		List<Repo> repositories = new ArrayList<>();

		repositories = Arrays.asList(repos);
		return repositories;
	}

	public String getBody(String url) throws IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(new HttpGet(url));
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		return responseString;
	}
	
	public Repo getRecentlyPushedRepo(List<Repo> repos) {
		Repo repo = repos.get(0);
		for(Repo repository : repos) {
			if(repo.getPushedAtAsDate().compareTo(repository.getPushedAtAsDate()) == -1)
				repo = repository;
		}
		return repo;
	}
	
	public Repo getRecentlyUpdatedRepo(List<Repo> repos) {
		Repo repo = repos.get(0);
		for(Repo repository : repos) {
			if(repo.getUpdatedAtAsDate().compareTo(repository.getUpdatedAtAsDate()) == -1)
				repo = repository;
		}
		return repo;
	}
}
