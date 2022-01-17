package ru.beryukhov.coffeegram.data

import com.google.android.gms.wearable.DataMap


fun DayCoffee.toDataMap(map: DataMap) {
    map.putIntegerArrayList(KEY, ArrayList(coffeeCountMap.entries.sortedBy { it.key.ordinal }.map {it.value})) //Fragile
}

fun ArrayList<Int>.toDayCoffee():DayCoffee {
    val m = mutableMapOf<CoffeeType, Int>()
    this.forEachIndexed { index, i ->
        m.put(CoffeeType.values()[index], i)
    }
    return DayCoffee(coffeeCountMap = m) //Fragile //todo test to and from
}

const val KEY = "coffeeCountMap"