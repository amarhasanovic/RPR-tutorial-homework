package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class StudentModel {
    private ObservableList<String> studenti = FXCollections.observableArrayList();
    private FilteredList<String> studentiFiltered = new FilteredList<>(studenti);

    public void napuni(){
        studenti.add("Student1");
        studenti.add("Student2");
        studenti.add("Student3");
        studenti.add("Student4");
        studenti.add("Student5");
        studenti.add("Student6");
        studenti.add("Student7");
        studenti.add("Student8");
        studenti.add("Student9");
        studenti.add("Student10");
        studenti.add("Student11");
        studenti.add("Student12");
        studenti.add("Student13");
        studenti.add("Student14");
        studenti.add("Student");
    }

    public ObservableList<String> getStudenti() {
        return studenti;
    }

    public void setStudenti(ObservableList<String> studenti) {
        this.studenti = studenti;
    }

    public FilteredList<String> getStudentiFiltered() {
        return studentiFiltered;
    }

    public void setStudentiFiltered(FilteredList<String> studentiFiltered) {
        this.studentiFiltered = studentiFiltered;
    }
}
