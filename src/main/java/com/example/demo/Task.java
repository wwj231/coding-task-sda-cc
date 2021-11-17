package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class Task {

  /*
  Zwróć listę aktywnych graczy posortowanych po ich wyniku malejąco
   */

  public static List<Person> getActivePlayersByScoreDesc(List<Person> people) {
    return people.stream()
            .filter(Person::isActive)
            .sorted(Comparator.comparing(Person::getScore).reversed())
            .collect(Collectors.toList());
  }

  /*
  Zwróć listę aktywnych graczy z danej grupy posortowanych po ich wyniku malejąco
   */

  public static List<Person> getActivePlayersByScoreDesc(List<Person> people, Group group) {
    return people.stream()
            .filter(p -> p.getTeam().equals(group))
            .filter(Person::isActive)
            .sorted(Comparator.comparing(Person::getScore).reversed())
            .collect(Collectors.toList());
  }

  /*
  Zwróć grupę, która posiada najwyższy wynik. Jeżeli wynik wielu grup jest taki sam, zwróć tę, która ma mniejszą liczbę aktywnych członków.
  Jeżeli ta liczba jest również równa, zwróć którąkolwiek z nich.
   */
  public static Group getGroupWithHighestScore(List<Person> people) {
    HashMap<Group, Integer> groupScoresMap = new HashMap<>();
    Group groupWithMaxScore = Group.G1;
    Integer maxScore = 0;

    for (Group group: Group.values()) {
      Integer score = people.stream().filter(p -> p.getTeam().equals(group)).map(Person::getScore).reduce(0, Integer::sum);
      if(score.compareTo(maxScore) > 0){
        groupWithMaxScore = group;
      }
      groupScoresMap.put(group, score);
    }

    if(Collections.frequency(groupScoresMap.values(), maxScore) > 1){
      List<Group> groupList = groupScoresMap.entrySet().stream().filter(n -> n.getValue().equals(maxScore)).map(Map.Entry::getKey).collect(Collectors.toList());

      long maxScoreGroupMembers = 0L;
      for (Person person : people) {
        if (person.getTeam().equals(groupWithMaxScore)) {
          if (person.isActive()) {
            maxScoreGroupMembers++;
          }
        }
      }

      for (Group group : groupList) {
        long groupMembers = people.stream()
                .filter(p -> p.getTeam().equals(group))
                .filter(Person::isActive).count();

          if (groupMembers < maxScoreGroupMembers){
            groupWithMaxScore = group;
            maxScoreGroupMembers = groupMembers;
          }
      }
      return groupWithMaxScore;
    }
    return groupWithMaxScore;
  }

  /*
  Zwróć listę wyników posortowaną malejąco na podstawie ilości punktów per zespół.
  Pojedynczy String powinien mieć format: "NazwaGrupy CałkowityWynik (lista_aktywnych_członków) [ilość nieaktywnych członków]"
   */

  public static List<String> printPoints(List<Person> people) {

    throw new RuntimeException("Not Implemented!");
  }
}
