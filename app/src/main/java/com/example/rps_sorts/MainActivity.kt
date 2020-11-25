package com.example.rps_sorts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    data class TS(var compare_count: Int, var transposition_count: Int)

    fun Bubble_Sort_Min_Max(data: DoubleArray, lenght: Int): TS {
        var ts = TS(0, 0)
        var temp: Double


        for (i in 0..lenght) {
            for (j in lenght - 1 downTo i + 1 step 1) {
                if (data[j] < data[j - 1]) {
                    temp = data[j]
                    data[j] = data[j - 1]
                    data[j - 1] = temp
                    ts.transposition_count++
                }
                ts.compare_count++
            }
        }
        return ts
    }

    fun Bubble_Sort_Max_Min(data: DoubleArray, lenght: Int): TS {
        var ts = TS(0, 0)
        var temp: Double


        for (i in 0..lenght) {
            for (j in lenght - 1 downTo i + 1 step 1) {
                if (data[j] > data[j - 1]) {
                    temp = data[j]
                    data[j] = data[j - 1]
                    data[j - 1] = temp
                    ts.transposition_count++
                }
                ts.compare_count++
            }
        }
        return ts
    }

    fun Quick_Sort_Min_Max(data: DoubleArray, begin: Int, end: Int): TS {
        var ts = TS(0, 0)
        var tstemp = TS(0, 0)
        var left = begin
        var right = end
        var pivot = data[(left + right) / 2]
        do {
            while (data[left] < pivot) {
                left++
                ts.compare_count++
            }
            ts.compare_count++
            while (data[right] > pivot) {
                right--
                ts.compare_count++
            }
            ts.compare_count++
            if (left <= right) {
                if (data[left] != data[right]) {
                    var temp = data[left]
                    data[left] = data[right]
                    data[right] = temp
                    ts.transposition_count++
                }
                left++
                right--
            }
        } while (left <= right)

        if (right > begin) {
            tstemp = Quick_Sort_Min_Max(data, begin, right)
            ts.transposition_count += tstemp.transposition_count
            ts.compare_count += tstemp.compare_count
        }
        if (left < end) {
            tstemp = Quick_Sort_Min_Max(data, left, end)
            ts.transposition_count += tstemp.transposition_count
            ts.compare_count += tstemp.compare_count
        }
        return (ts)
    }

    fun Quick_Sort_Max_Min(data: DoubleArray, begin: Int, end: Int): TS {
        var ts = TS(0, 0)
        var tstemp = TS(0, 0)
        var left = begin
        var right = end
        var pivot = data[(left + right) / 2]
        do {
            while (data[left] > pivot) {
                left++
                ts.compare_count++
            }
            ts.compare_count++
            while (data[right] < pivot) {
                right--
                ts.compare_count++
            }
            ts.compare_count++
            if (left <= right) {
                if (data[left] != data[right]) {
                    var temp = data[left]
                    data[left] = data[right]
                    data[right] = temp
                    ts.transposition_count++
                }
                left++
                right--
            }
        } while (left <= right)
        if (right > begin) {
            tstemp = Quick_Sort_Max_Min(data, begin, right)
            ts.transposition_count += tstemp.transposition_count
            ts.compare_count += tstemp.compare_count
        }
        if (left < end) {
            tstemp = Quick_Sort_Max_Min(data, left, end)
            ts.transposition_count += tstemp.transposition_count
            ts.compare_count += tstemp.compare_count
        }
        return (ts)
    }

    fun Sub_Merge_Sort_Min_Max(data: DoubleArray, first: Int, last: Int, mid: Int): TS {
        var ts = TS(0, 0)
        var i: Int
        var j: Int
        var k: Int
        var temp_array: Array<Double> = Array(last - first + 1) { 0.0 }
        i = first
        k = 0
        j = mid + 1
        while ((i <= mid) && (j <= last)) {
            ts.compare_count++
            if (data[i] <= data[j]) {
                temp_array[k] = data[i]
                k++
                i++
            } else {
                temp_array[k] = data[j]
                ts.transposition_count++
                k++
                j++
            }
        }
        while (i <= mid) {
            temp_array[k] = data[i]
            k++
            i++
        }
        while (j <= last) {
            temp_array[k] = data[j]
            k++
            j++
        }
        for (i in first..last) {
            data[i] = temp_array[i - first]
        }
        return ts
    }

    fun Merge_Sort_Min_Max(data: DoubleArray, first: Int, last: Int): TS {
        var ts = TS(0, 0)
        var tstemp = TS(0, 0)
        var mid: Int
        if (first < last) {
            mid = (first + last) / 2
            tstemp = Merge_Sort_Min_Max(data, first, mid)
            ts.compare_count += tstemp.compare_count
            ts.transposition_count += tstemp.transposition_count
            tstemp = Merge_Sort_Min_Max(data, mid + 1, last)
            ts.compare_count += tstemp.compare_count
            ts.transposition_count += tstemp.transposition_count
            tstemp = Sub_Merge_Sort_Min_Max(data, first, last, mid)
            ts.compare_count += tstemp.compare_count
            ts.transposition_count += tstemp.transposition_count
        }
        return ts
    }

    fun Sub_Merge_Sort_Max_Min(data: DoubleArray, first: Int, last: Int, mid: Int): TS {
        var ts = TS(0, 0)
        var i: Int
        var j: Int
        var k: Int
        var temp_array: Array<Double> = Array(last - first + 1) { 0.0 }
        i = first
        k = 0
        j = mid + 1
        while ((i <= mid) && (j <= last)) {
            ts.compare_count++
            if (data[i] >= data[j]) {
                temp_array[k] = data[i]
                k++
                i++
            } else {
                temp_array[k] = data[j]
                ts.transposition_count++
                k++
                j++
            }
        }
        while (i <= mid) {
            temp_array[k] = data[i]
            k++
            i++
        }
        while (j <= last) {
            temp_array[k] = data[j]
            k++
            j++
        }
        for (i in first..last) {
            data[i] = temp_array[i - first]
        }
        return ts
    }

    fun Merge_Sort_Max_Min(data: DoubleArray, first: Int, last: Int): TS {
        var ts = TS(0, 0)
        var tstemp = TS(0, 0)
        var mid: Int
        if (first < last) {
            mid = (first + last) / 2
            tstemp = Merge_Sort_Max_Min(data, first, mid)
            ts.compare_count += tstemp.compare_count
            ts.transposition_count += tstemp.transposition_count
            tstemp = Merge_Sort_Max_Min(data, mid + 1, last)
            ts.compare_count += tstemp.compare_count
            ts.transposition_count += tstemp.transposition_count
            tstemp = Sub_Merge_Sort_Max_Min(data, first, last, mid)
            ts.compare_count += tstemp.compare_count
            ts.transposition_count += tstemp.transposition_count
        }
        return ts
    }

    fun Insertion_Sort_Min_Max(data: DoubleArray, lenght: Int): TS {
        var ts = TS(0, 0)
        var temp: Double
        var i: Int
        var j: Int
        for (i in 1..lenght - 1) {
            ts.compare_count++
            j = i
            while ((j > 0) && (data[j - 1] > data[j])) {
                temp = data[j - 1]
                data[j - 1] = data[j]
                data[j] = temp
                ts.transposition_count++
                j--
            }
        }
        return ts
    }

    fun Insertion_Sort_Max_Min(data: DoubleArray, lenght: Int): TS {
        var ts = TS(0, 0)
        var temp: Double
        var i: Int
        var j: Int
        for (i in 1..lenght - 1) {
            ts.compare_count++
            j = i
            while ((j > 0) && (data[j - 1] < data[j])) {
                temp = data[j - 1]
                data[j - 1] = data[j]
                data[j] = temp
                ts.transposition_count++
                j--
            }
        }
        return ts
    }



    fun sort(view: View) {


        //merge gotovo (data, 0, data.size-1)
        //puzir gotovo (a,a.size)
        //insrtion gotovo (a,a.size)
        //quick gotovo (a,0,a.size-1)

        sorts.visibility=View.VISIBLE

        var a = data1.clone()
        val typeSort = spinner.selectedItemPosition
        when (typeSort){
            0 -> {
                a = data1.clone()
                var tsBubble = TS(0, 0)
                if (switch1.isChecked) {
                    tsBubble = Bubble_Sort_Max_Min(a, a.size)
                } else {
                    tsBubble = Bubble_Sort_Min_Max(a, a.size)
                }
                textView27.text = ("Пузырьком")
                textView30.text = (tsBubble.transposition_count.toString())
                textView31.text = (tsBubble.compare_count.toString())

                row2.visibility = View.INVISIBLE
                row3.visibility = View.INVISIBLE
                row4.visibility = View.INVISIBLE
            }
            1 -> {
                a = data1.clone()
                var tsInsertion = TS(0, 0)
                if (switch1.isChecked) {
                    tsInsertion = Insertion_Sort_Max_Min(a, a.size)
                } else {
                    tsInsertion = Insertion_Sort_Min_Max(a, a.size)
                }
                textView27.text = ("Вставкой")
                textView30.text = (tsInsertion.transposition_count.toString())
                textView31.text = (tsInsertion.compare_count.toString())
                row2.visibility = View.INVISIBLE
                row3.visibility = View.INVISIBLE
                row4.visibility = View.INVISIBLE
            }
            2 -> {
                a = data1.clone()
                var tsMerge = TS(0, 0)
                if (switch1.isChecked) {
                    tsMerge = Merge_Sort_Max_Min(a, 0, a.size - 1)
                } else {
                    tsMerge = Merge_Sort_Min_Max(a, 0, a.size - 1)
                }
                textView27.text = ("Слиянием")
                textView30.text = (tsMerge.transposition_count.toString())
                textView31.text = (tsMerge.compare_count.toString())
                row2.visibility = View.INVISIBLE
                row3.visibility = View.INVISIBLE
                row4.visibility = View.INVISIBLE
            }
            3 -> {
                a = data1.clone()
                var tsQuick = TS(0, 0)
                if (switch1.isChecked) {
                    tsQuick = Merge_Sort_Max_Min(a, 0, a.size - 1)
                } else {
                    tsQuick = Merge_Sort_Min_Max(a, 0, a.size - 1)
                }
                textView27.text = ("Быстрая")
                textView30.text = (tsQuick.transposition_count.toString())
                textView31.text = (tsQuick.compare_count.toString())
                row2.visibility = View.INVISIBLE
                row3.visibility = View.INVISIBLE
                row4.visibility = View.INVISIBLE
            }
            4 -> {
                row2.visibility = View.VISIBLE
                row3.visibility = View.VISIBLE
                row4.visibility = View.VISIBLE
                var tsBubble = TS(0, 0)
                var tsInsertion = TS(0, 0)
                var tsMerge = TS(0, 0)
                var tsQuick = TS(0, 0)
                a = data1.clone()
                if (switch1.isChecked) {
                    tsBubble = Bubble_Sort_Max_Min(a, a.size)
                    a = data1.clone()
                    tsInsertion = Insertion_Sort_Max_Min(a, a.size)
                    a = data1.clone()
                    tsMerge = Merge_Sort_Max_Min(a, 0, a.size - 1)
                    a = data1.clone()
                    tsQuick = Quick_Sort_Max_Min(a, 0, a.size - 1)
                } else {
                    tsBubble = Bubble_Sort_Min_Max(a, a.size)
                    a = data1.clone()
                    tsInsertion = Insertion_Sort_Min_Max(a, a.size)
                    a = data1.clone()
                    tsMerge = Merge_Sort_Min_Max(a, 0, a.size - 1)
                    a = data1.clone()
                    tsQuick = Quick_Sort_Min_Max(a, 0, a.size - 1)
                }
                textView27.text = ("Пузырьком")
                textView30.text = (tsBubble.transposition_count.toString())
                textView31.text = (tsBubble.compare_count.toString())

                textView32.text = ("Вставкой")
                textView33.text = (tsInsertion.transposition_count.toString())
                textView34.text = (tsInsertion.compare_count.toString())

                textView35.text = ("Слиянием")
                textView36.text = (tsMerge.transposition_count.toString())
                textView37.text = (tsMerge.compare_count.toString())

                textView38.text = ("Быстрая")
                textView39.text = (tsQuick.transposition_count.toString())
                textView40.text = (tsQuick.compare_count.toString())
            }
            5 -> {
                row2.visibility = View.VISIBLE
                var tsMerge = TS(0, 0)
                var tsQuick = TS(0, 0)
                a = data1.clone()
                if (switch1.isChecked) {
                    tsMerge = Merge_Sort_Max_Min(a, 0, a.size - 1)
                    a = data1.clone()
                    tsQuick = Quick_Sort_Max_Min(a, 0, a.size - 1)
                } else {
                    tsMerge = Merge_Sort_Min_Max(a, 0, a.size - 1)
                    a = data1.clone()
                    tsQuick = Quick_Sort_Min_Max(a, 0, a.size - 1)
                }
                textView27.text = "Слиянием"
                textView30.text = (tsMerge.transposition_count.toString())
                textView31.text = (tsMerge.compare_count.toString())

                textView32.text = ("Быстрая")
                textView33.text = (tsQuick.transposition_count.toString())
                textView34.text = (tsQuick.compare_count.toString())

                row3.visibility = View.INVISIBLE
                row4.visibility = View.INVISIBLE
            }
        }
    }


    private val CHOOSE_THIEF = 0
    lateinit var data1:DoubleArray

    fun fillArray(view: View) {
        sorts.visibility=View.INVISIBLE
        val arrayIntent = Intent(this, arrayFilling::class.java)

        startActivityForResult(arrayIntent, CHOOSE_THIEF)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, result: Intent?) {
        when (requestCode) {
            0 -> if (resultCode == RESULT_OK) {
                if (result != null) {
                    data1 = result.extras!!.getDoubleArray("DIGITS")!!
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, result)
        }
    }






}