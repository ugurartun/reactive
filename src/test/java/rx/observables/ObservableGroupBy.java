package rx.observables;

import org.junit.Test;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Group by can be very handy when you need create items groups in your pipeline, instead of filter by just one item type,
 * you can create those groups and then when the observable finish, on complete you will have a GroupedObservable, which is a map
 * with the pair key(filter)/value(item emitted)
 */
public class ObservableGroupBy {

    /**
     * Created a Boolen GroupedObservable
     */
    @Test
    public void testGroupBy() {
        Observable.just(getPersons())
                  .flatMap(listOfPersons -> Observable.from(listOfPersons)
                                                      .groupBy(person -> person.sex.equals("male")))
                  .subscribe(booleanPersonGroupedObservable -> {
                      if (booleanPersonGroupedObservable.getKey()) {
                          booleanPersonGroupedObservable.asObservable()
                                                        .subscribe(person -> System.out.println("Here the male:" + person.name));
                      } else {
                          booleanPersonGroupedObservable.asObservable()
                                                        .subscribe(person -> System.out.println("Here the female:" + person.name));
                      }
                  });
    }


    /**
     * Created a GroupedObservable by sex
     */
    @Test
    public void testGroupBySex() {
        Observable.just(getPersons())
                  .flatMap(listOfPersons -> Observable.from(listOfPersons)
                                                      .groupBy(person -> person.sex))
                  .subscribe(booleanPersonGroupedObservable -> {
                      switch (booleanPersonGroupedObservable.getKey()) {
                          case "male": {
                              booleanPersonGroupedObservable.asObservable()
                                                            .subscribe(person -> System.out.println("Here the male:" + person.name));
                              break;
                          }
                          case "female": {
                              booleanPersonGroupedObservable.asObservable()
                                                            .subscribe(person -> System.out.println("Here the female:" + person.name));
                              break;
                          }
                      }
                  });
    }

    private List<Person> getPersons() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Pablo", 34, "male"));
        people.add(new Person("Paula", 35, "female"));
        return people;
    }

}