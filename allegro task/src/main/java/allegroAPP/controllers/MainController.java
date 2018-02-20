package allegroAPP.controllers;

import java.util.ArrayList;
import java.util.List;

import allegroAPP.main.AllegroRepos;
import allegroAPP.main.Repo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {

	@FXML
	private TableView<Repo> reposTableView;

	@FXML
	private TableColumn<Repo, String> nameColumn;

	@FXML
	private TableColumn<Repo, String> pushedColumn;

	@FXML
	private TableColumn<Repo, String> updatedColumn;

	@FXML
	private Label recentlyPushedLabel;

	@FXML
	private Label recentlyUpdatedLabel;

	private List<Repo> repos = new ArrayList<>();

	private AllegroRepos allegroRepos = new AllegroRepos();

	private enum Order {
		NAME, PUSH, UPDATE
	}

	private Order order = Order.NAME;

	@FXML
	public void initialize() {

		repos = allegroRepos.getRepos();

		recentlyPushedLabel.setText(allegroRepos.getRecentlyPushedRepo(repos).getName());
		recentlyUpdatedLabel.setText(allegroRepos.getRecentlyUpdatedRepo(repos).getName());

		reposTableView.setItems(FXCollections.observableArrayList(repos));

		nameColumn.setCellValueFactory(cellData -> {
			SimpleStringProperty name = new SimpleStringProperty(cellData.getValue().getName());
			return name;
		});

		updatedColumn.setCellValueFactory(cellData -> {
			SimpleStringProperty updated = new SimpleStringProperty(
					cellData.getValue().getUpdatedAtAsDate().toString());
			return updated;
		});

		pushedColumn.setCellValueFactory(cellData -> {
			SimpleStringProperty pushed = new SimpleStringProperty(cellData.getValue().getPushedAtAsDate().toString());
			return pushed;
		});
		

	}

	@FXML
	public void refresh() {
		repos = allegroRepos.getRepos();
		recentlyPushedLabel.setText(allegroRepos.getRecentlyPushedRepo(repos).getName());
		recentlyUpdatedLabel.setText(allegroRepos.getRecentlyUpdatedRepo(repos).getName());
		if (order == Order.NAME)
			sortByName();
		else if (order == Order.PUSH)
			sortByPush();
		else if (order == Order.UPDATE)
			sortByUpdate();

	}

	@FXML
	public void sortByName() {
		repos.sort((repo1, repo2) -> repo1.getName().toLowerCase().compareTo(repo2.getName().toLowerCase()));
		reposTableView.setItems(FXCollections.observableArrayList(repos));
		reposTableView.refresh();
		order = Order.NAME;
	}

	@FXML
	public void sortByUpdate() {
		repos.sort((repo2, repo1) -> repo1.getUpdatedAtAsDate().compareTo(repo2.getUpdatedAtAsDate()));
		reposTableView.setItems(FXCollections.observableArrayList(repos));
		reposTableView.refresh();
		order = Order.UPDATE;

	}

	@FXML
	public void sortByPush() {
		repos.sort((repo2, repo1) -> repo1.getPushedAtAsDate().compareTo(repo2.getPushedAtAsDate()));
		reposTableView.setItems(FXCollections.observableArrayList(repos));
		reposTableView.refresh();
		order = Order.PUSH;

	}

}
