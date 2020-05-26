package cheema.hardeep.sahibdeep.studentify.models.tables

import cheema.hardeep.sahibdeep.studentify.R2.id.name

final String s = "sahib";
s = "hardeep";

int num = 5;
double num2 = 6.00;

MyAdapter adapter = new MyAdapter()

Sahib obj = new Sahib();

String x = null;
List<String> listOfStrings = new ArrayList();
for(int i=0; i<lisyOfStrings.size(); i++){

}
class Student {
    String name;
    String rollNumber;
    int age;

    String getName(){
        return name;
    }
    void setName (String name){
        this.name = name;
    }
    ...
}
class Test {
    val s: String = "yes"
    var x: String = "no"

    var num: Int = 5
    var num2: Double = 6.00

    var single: String = "5"

    val adapter = MyAdapter()
    val sahib = Sahib()

    val list = listOf<String>("Test", "Test2", "Sahib3", "Sahib4")
    val list2 = mutableListOf<String>()

    val map = mutableMapOf<String, String>()


    fun test() {
        s = "test"
        x = "sahib"
        val y: String? = null

        val y2: Int = y?.toInt() ?: 0

        var result = single.toInt()

        val first = list[1]
        list2.addAll(list)
        map.put("Sahib", "Programmer")

        list.forEach {
            println(it)
        }

        val filteredList: List<String> = list.filter { it.length == 3 }

        val changeList: List<String> = list.map { "Hardeep " + it }
        val changeList2: List<Int> = list.map { it.length }

        var hello: Hello = Hello("Sahib", "345", 23)
        hello.age = 26
        //var hello2: Hello = Hello(null, "345", 23)

    }
}

data class Hello(var name: String? = "", var rollNumber: String, var age: Int? = 23)