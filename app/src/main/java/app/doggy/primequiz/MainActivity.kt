package app.doggy.primequiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //問題を格納する変数。
    var questionNum = 2

//    //正誤判定の別パターン。
//    //正誤判定の結果を格納する変数。
//    var answer = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //最初の問題を生成。
        makeQuestion()

//        //makeQuestion()の別パターン。
//        questionNum = makeQuestion()

        //クリックリスナを設定。
        rightButton.setOnClickListener(JudgeListener())
        wrongButton.setOnClickListener(JudgeListener())
    }

    private inner class JudgeListener: View.OnClickListener {
        override fun onClick(view: View) {

            //ボタンのidに応じて処理を変える。
            when(view.id) {
                R.id.rightButton -> {
                    when(checkAnswer(questionNum)) {
                        true -> resultTextView.text = "正解！"
                        false -> resultTextView.text = "間違い！"
                    }

//                    //正誤判定の別パターン。
//                    when(answer) {
//                        true -> resultTextView.text = "Right !"
//                        false -> resultTextView.text = "Wrong !"
//                    }
                }

                R.id.wrongButton -> {
                    when(checkAnswer(questionNum)) {
                        true -> resultTextView.text = "間違い！"
                        false -> resultTextView.text = "正解！"
                    }
                }
            }

            //文字色を変更。
            when(resultTextView.text) {
                "正解！" -> resultTextView.setTextColor(Color.parseColor("#4ada4a"))
                "間違い！" -> resultTextView.setTextColor(Color.parseColor("#da4a4a"))
            }

            //次の問題を生成。
            makeQuestion()

        }
    }

    //問題を生成。
    private fun makeQuestion(){
        //ランダムに整数を決定。
        questionNum = Random.nextInt(2, 100)

        //整数を表示。
        questionNumTextView.text = questionNum.toString()

    }

//    //makeQuestion()の別パターン。
//    private fun makeQuestion(): Int{
//        //ランダムに整数を決定。
//        var questionNum = Random.nextInt(2, 100)
//
//        //整数を表示。
//        questionNumTextView.text = questionNum.toString()
//
//        return questionNum
//    }

    //正誤判定。
    private fun checkAnswer(questionNum: Int) :Boolean{
        //正誤判定の結果を格納する変数。
        var answer = true

        //素数かどうか判定。
        for (i in 2 until questionNum-1) {
            if (questionNum%i == 0) {
                answer = false
                reasonTextView.text = "${questionNum}は${i}の倍数だ！"
                break
            } else {
                reasonTextView.text = "${questionNum}は素数だ！"
            }
        }

        return answer

    }

//    //正誤判定の別パターン。
//    private fun checkAnswer(questionNum: Int){
//        //素数かどうか判定。
//        for (i in 2 until questionNum-1) {
//            if (questionNum%i == 0) {
//                answer = false
//                reasonTextView.text = "It's a multiple of ${i}"
//                break
//            } else {
//                reasonTextView.text = "It's a prime number"
//            }
//        }
//    }

}