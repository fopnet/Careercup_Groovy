import groovy.transform.EqualsAndHashCode

/**
 Given a set of n people, find the celebrity.
 Celebrity is a person who:
 1. Knows only himself and no one else
 2. Every one else knows this person

 You are given the following helper function:
 bool knows(i, j);
 Returns:
 True: If i knows j
 False: otherwise

 I proposed the O(n2) algorithm at first but he wanted me to improve on it. He wanted an O(n) algorithm

 Ex: A->B->C
     A->C<-D

 A->C means A knows C
 C<-A means C is Known for A
 C is celebrity

 * Created by Felipe on 21/03/2015.
 */
class Celebrity {

    static class Person {
        private String name
        List<Person> knows = new ArrayList<>()
        List<Person> isknown = new ArrayList<>()

        // the ideal would be a structure that was a list and set together
        //private static Set<Person> friendsSet = new TreeSet<Person>([compare:{a,b->  b.knows.size() <=> a.knows.size() }] as Comparator)
        private static Set<Person> friendsSet = new LinkedHashSet<Person>()
        private static List<Person> friendsList = new ArrayList<>()

        Person know(Person p) {
            knows << p
            p.isknown << this
            addFriends(p)
            addFriends(this)
            p
        }

        Person isKnown(Person p) {
            isknown  << p
            p.knows << this
            addFriends(p)
            addFriends(this)
            p
        }

        private void addFriends(Person p) {
            if (friendsSet.add( p )) {
                friendsList.add( p )
            }
        }

        boolean knows(i, j) {
            if (i>=0 && j>=0 && i < friendsSet.size() && j < friendsList.get(i).knows.size()) {
                friendsList.get(i).knows.get(j) != null
            }
            false
        }

        Person findCelebrity() {

            // with java 7 using the TimSort Average case performance  O(n log n)
            //def cmp =  [compare:{a,b->  b.knows.size() <=> a.knows.size() }] as Comparator
            friendsSet = friendsSet.sort(true) {a,b->  b.knows.size() <=> a.knows.size() }

            friendsSet.find { Person p ->
                // Every one else knows this person
                (p.isknown.size() == friendsSet.size() -1) && (p.knows.size() == 0) //Knows only himself and no one else
            }

        }
    }

    static void main (String[] args) {
        def personA = new Person(name: "a")
        def personC = new Person(name: "c")
        def a = personA
                    .know(new Person(name:"b"))
                    .know(personC)
                    .isKnown(new Person(name:"d"))
        personA.know(personC)

        println "Celebrity is Person ${personA.findCelebrity()?.name}"

    }
}
