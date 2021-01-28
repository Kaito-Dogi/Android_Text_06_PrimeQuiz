package app.doggy.primequiz

//関数内で使う変数をActivity内で宣言してもOKか。
//makeQuestion()では戻り値を返すようにした方が良いか。
//->関数内で変数を使うことになるので、こっちの方が良さそう？
//makeQuestion()内に、問題作成とは直接関係ないTextViewに表示する処理も書いて良いか（読みやすいか）。

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

//      //makeQuestion()の別パターン。
//      questionNum = makeQuestion()

        //クリックリスナを設定。
        rightButton.setOnClickListener(JudgeListener())
        wrongButton.setOnClickListener(JudgeListener())
    }

    private inner class JudgeListener: View.OnClickListener {
        override fun onClick(view: View) {

            //ボタンのidに応じて処理を変える。
            when(view.id) {
                R.id.rightButton -> {
                    when(judgeAnswer(questionNum)) {
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
                    when(judgeAnswer(questionNum)) {
                        true -> resultTextView.text = "間違い！"
                        false -> resultTextView.text = "正解！"
                    }
                }
            }

            //正誤に応じて処理を変える。
            when(resultTextView.text) {
                //文字色を変更。
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
    private fun judgeAnswer(questionNum: Int) :Boolean{
        //正誤判定の結果を格納する変数。
        var answer = true

        //素数かどうか判定。
        for (i in 2 until questionNum) {
            if (questionNum%i == 0) {
                answer = false
                reasonTextView.text = "${questionNum}は${i}の倍数だ！"
                Log.d("judge", "${questionNum}は${i}の倍数だ！")
                break
            } else if (i+1 == questionNum) {
                reasonTextView.text = "${questionNum}は素数だ！"
                Log.d("judge", "${questionNum}は素数だ！")
            }
        }

        Log.d("judge", answer.toString())
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