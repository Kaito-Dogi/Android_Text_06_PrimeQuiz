package app.doggy.primequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        const val QUESTION_COUNT = 10
    }

    //問題を格納する変数。
    var questionNum = 2

    //正誤判定の結果を格納する変数。
    var answer = true

    //Toastで表示する文字列。
    var toastMessage: String = ""

    //今何問目かを管理する変数。
    var status = 0

    //正解数をカウントする変数。
    var correctCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //最初の問題を生成。
        makeQuestion()

        //クリックリスナを設定。
        rightButton.setOnClickListener(JudgeListener())
        wrongButton.setOnClickListener(JudgeListener())
    }

    private inner class JudgeListener: View.OnClickListener {
        override fun onClick(view: View) {

            //正誤判定。
            judgeAnswer(questionNum)

            //ボタンのidに応じて処理を変える。
            when(view.id) {
                R.id.rightButton -> {
                    when (answer) {
                        true -> {
                            toastMessage = "正解！$toastMessage"
                            correctCount += 1
                        }
                        false -> toastMessage = "間違い！$toastMessage"
                    }
                }

                R.id.wrongButton -> {
                    when (answer) {
                        true -> toastMessage = "間違い！$toastMessage"
                        false -> {
                            toastMessage = "正解！$toastMessage"
                            correctCount += 1
                        }
                    }
                }
            }

            //何問目かによって処理を変える。
            if (status == QUESTION_COUNT) {

                //正誤を表示。
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()

                //TextViewに結果を表示。
                explanationTextView.text = "ボタンを押して再チャレンジ！"
                questionNumTextView.text = "${correctCount}問正解！"

                //statusを更新。
                status = -1

            } else if(status == -1) {
                //リセット。
                status = 0
                correctCount = 0
                explanationTextView.setText(R.string.explanatory_text)

                //最初の問題を生成。
                makeQuestion()

            } else {

                //正誤を表示。
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()

                //次の問題を生成。
                makeQuestion()

            }

        }

    }

    //問題を生成。
    private fun makeQuestion(){
        //ランダムに整数を決定。
        questionNum = Random.nextInt(2, 100)

        //整数を表示。
        questionNumTextView.text = questionNum.toString()

        //statusを更新。
        status += 1

    }

    //正誤判定。
    private fun judgeAnswer(questionNum: Int) {

        //2かどうか判定。
        if (questionNum == 2) {

            //questionNumは素数なので、true。
            answer = true

            toastMessage = "(素数)"

            //素数か否かの判定結果をログ出力。
            Log.d("judge", "${questionNum}:素数")

        } else {

            //素数かどうか判定。
            for (i in 2 until questionNum) {

                if (questionNum%i == 0) {

                    //questionNumは素数ではないので、false。
                    answer = false

                    toastMessage = "(${i}の倍数)"

                    //素数か否かの判定結果をログ出力。
                    Log.d("judge", "${questionNum}:${i}の倍数")

                    break

                } else if (i+1 == questionNum) {

                    //questionNumは素数なので、true。
                    answer = true

                    toastMessage = "(素数)"

                    //素数か否かの判定結果をログ出力。
                    Log.d("judge", "${questionNum}:素数")
                }
            }
        }

        //正誤判定をログ出力。
        Log.d("judge", answer.toString())
    }

}