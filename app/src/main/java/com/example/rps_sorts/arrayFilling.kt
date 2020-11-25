package com.example.rps_sorts


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_array_filling.*
import java.util.*


open class arrayFilling : AppCompatActivity() {

    public var data: MutableList<Double> = mutableListOf();


    fun addRow(c0: Int, c1: Double) {
        //Сначала найдем в разметке активити саму таблицу по идентификатору
        val tableLayout = findViewById<View>(R.id.table) as TableLayout
        //Создаём экземпляр инфлейтера, который понадобится для создания строки таблицы из шаблона. В качестве контекста у нас используется сама активити
        val inflater = LayoutInflater.from(this)
        //Создаем строку таблицы, используя шаблон из файла /res/layout/table_row.xml
        val tr: TableRow = inflater.inflate(R.layout.table_row, null) as TableRow
        //Находим ячейку для номера дня по идентификатору
        var tv = tr.findViewById(R.id.col1) as TextView
        var num = tr.findViewById(R.id.col2) as EditText
        //Обязательно приводим число к строке, иначе оно будет воспринято как идентификатор ресурса
        num.setText(c1.toString())

        tv.text = c0.toString()
        //...и так далее для всех значений
        tableLayout.addView(tr) //добавляем созданную строку в таблицу
    }

    companion object {
        const val COUNT = "count"
    }


    fun fillTable(data: MutableList<Double>, count: Int) {

        table.removeAllViews()

        for (i in 0 until count) {
            /*
            var row = table.getChildAt(i) as TableRow
            var digit = row.getChildAt(1) as EditText
            digit.setText(data[i].toString())
            */
            addRow(i + 1, data[i])
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_array_filling)
        val spnLocale = findViewById<View>(R.id.spinner2) as Spinner



        spnLocale.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val typeFilling = spnLocale.selectedItemPosition
                when (typeFilling) {
                    0 -> {
                        textView4.visibility = View.INVISIBLE
                        editTextNumber2.visibility = View.INVISIBLE
                    }
                    1 -> {
                        textView4.visibility = View.VISIBLE
                        editTextNumber2.visibility = View.VISIBLE
                    }
                    2 -> {
                        textView4.visibility = View.INVISIBLE
                        editTextNumber2.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }
    }




    fun fill2(view: View) {
        val random = Random()
        val count = editTextNumber3.text.toString().toInt()
        val typeFilling = spinner2.selectedItemPosition
        data.clear()
        if (count < 3000) {
            //addRow(i, random.nextInt(100).toDouble())
            when (typeFilling) {
                0 -> {
                    data.add( random.nextInt(5) + 1.toDouble())
                    for (i in 1 until count) {
                        data.add(random.nextInt(10) + data[i - 1].toDouble())

                    }
                    fillTable(data, count)
                }

                1 -> {
                    data.add(random.nextInt(5) + 1.toDouble())
                    for (i in 1 until count) {
                        data.add( random.nextInt(10) + data[i - 1].toDouble())
                    }
                    var chaos = editTextNumber2.text.toString().toDouble()
                    var k: Int
                    var n: Int
                    var temp: Double
                    for (i in 0 until (count * (chaos / 100).div(1)).toInt()) {
                        k = random.nextInt(count)
                        n = random.nextInt(count)
                        temp = data[k]
                        data[k] = data[n]
                        data[n] = temp
                    }
                    fillTable(data, count)
                }

                2 -> {
                    for (i in 0 until count) {
                        data.add(random.nextInt(1000).toDouble())
                    }
                    fillTable(data, count)
                }
            }
        } else {
            table.visibility = View.INVISIBLE
            //addRow(i, random.nextInt(100).toDouble())
            when (typeFilling) {
                0 -> {
                    data.add(random.nextInt(5) + 1.toDouble())
                    for (i in 1 until count) {
                        data.add(random.nextInt(10) + data[i - 1].toDouble())
                    }
                }

                1 -> {
                    data.add(random.nextInt(5) + 1.toDouble())
                    for (i in 1 until count) {
                        data.add(random.nextInt(10) + data[i - 1].toDouble())
                    }
                    var chaos = editTextNumber2.text.toString().toDouble()
                    var k: Int
                    var n: Int
                    var temp: Double
                    for (i in 0 until (count * (chaos / 100).div(1)).toInt()) {
                        k = random.nextInt(count)
                        n = random.nextInt(count)
                        temp = data[k]
                        data[k] = data[n]
                        data[n] = temp
                    }
                }

                2 -> {
                    for (i in 0 until count) {
                        data.add(random.nextInt(1000).toDouble())
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        var arrayFillingIntent =  Intent()
        var count = editTextNumber3.text.toString().toInt()

        val b = Bundle()
        if (count < 3000) {
            data.clear()
            for (i in 0 until count) {
                var row = table.getChildAt(i) as TableRow
                var digit = row.getChildAt(1) as EditText
                data.add(digit.text.toString().toDouble())
            }
        }
        var numbers = DoubleArray(count){0.0}

        for (i in 0 until count){
            numbers[i]=data[i]
        }

        b.putDoubleArray("DIGITS",numbers)
        arrayFillingIntent.putExtras(b)
        setResult(RESULT_OK, arrayFillingIntent)
        finish()
    }
}