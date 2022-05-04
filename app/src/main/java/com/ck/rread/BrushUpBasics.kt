package com.ck.rread

import java.util.*


fun main() {

    val brushUpBasics = BrushUpBasics()
//    brushUpBasics.newFun()
//brushUpBasics.myFun()
//    brushUpBasics.forLoopOne()
//    brushUpBasics.reverseFront()
    brushUpBasics.reverseBack()
//    brushUpBasics.forLoop2()
//    brushUpBasics.printStar()

//    brushUpBasics.printingStar()
}
class BrushUpBasics {
     fun myFun() {
         for (i in 1..10) {
if (i % 2 ==  0)

    for (j in 1..3)
             print(" $j")

         }
     }fun reverseBack() {


        var I = ""
        var J = ""

        var sc = Scanner(System.`in`)

        println("Enter a word")

        var str = sc.nextLine()
        var word = str.length
        var k = 1
        var n1 = CharArray(word)

        for(i in 0..n1.size-1){
            for (k in 1+i..n1.size-1){

                J = J+ str[k]
            }

            I = I+str[i]
            println(" "+J +" "+I)
            J = ""
        }

    }
    fun reverseFront() {

        fun reverseFront() {
            var I = ""
            var J = ""

            var sc = Scanner(System.`in`)

            println("Enter a word")

            var str = sc.nextLine()
            var n = str.length
            println(n)
            var k = 1
            var n1 = CharArray(n)

            for (i in n - 1 downTo 0) {
                var j = 0
                while (j < n - k) {
                    J = J + str[j]
                    j++
                }
                k++

                I = str[i] + I // o
                println(I + " " + J)
                J = ""
            }


        }

        fun forLoopOne() {

            var count = 5
            val number = arrayOf(10, 20, 30, 40, 50)
            val str = "clement"
            val length = str.length
            println("the size of the length clement is $length")
            var n = number.size
            println("the size of number array is $n")

            for (i in 0..n - 1) {

                for (j in n - 1..1) {

                    print(" $j")

                }
                println()


            }
        }

        fun forLoop2() {

            val number = arrayOf(5, 4, 3, 2, 1)
            println("This is second method")
            for (i in number) {

                print(i)
            }

        }

        fun printStar() {
            for (i in 0..5) {
                for (j in 0 until i) {
                    print("*")
                }
                println()
            }
        }
    }
    fun printingStar(){

        val sc = Scanner(System.`in`)
        println("Enter your number")
        val n =sc.nextInt()
        val a = IntArray(n)
        //
        for(i in a.size downTo 0 ){
            for (j in 1 until i+1){
                print(" "+i)
            }
            println()
        }
    }
    fun newFun(){
        /*val sc = Scanner(System.`in`)
        println("Enter your number")

        val number = sc.nextLine().toInt()
*/
//        val a = arrayOf(5,4,3,2,1)
        val a = arrayOf(1,1,1,1,1)
        var n = a.size

        for (i in a.size downTo 1){
            for (j in 0..i-1 ){
                print(a[j])

            }
            println()
        }
    }


}