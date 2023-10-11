package com.example.snake


import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.snake.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var gameOver = true
    private var start = true
    private var speed = 250L
    private lateinit var music: MediaPlayer
    private lateinit var death: MediaPlayer
    private lateinit var eating: MediaPlayer

    private val timer: CountDownTimer = object : CountDownTimer(1000000000, speed) {
        override fun onFinish() {
            start()
        }

        override fun onTick(p0: Long) {
            if (!gameOver) {
                runOnUiThread {
                    binding.playground.invalidate()
                }

            } else {
                if (!start) {
                    binding.gameOverTextView.visibility = View.VISIBLE
                }
                if (music.isPlaying) {
                    music.pause()
                    death.start()
                }

                binding.actionButton.text = resources.getString(R.string.new_game)
                cancel()
            }
        }
    }.start()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        music = MediaPlayer.create(this, R.raw.wakawaka4)
        music.isLooping = true
        death = MediaPlayer.create(this, R.raw.death1)
        eating = MediaPlayer.create(this, R.raw.bite2)

        binding.apply {
            actionButton.setOnClickListener {
                if (gameOver) {
                    music.isLooping = true
                    music.start()
                    gameOver = false
                    playground.score = 0
                    playground.snake = null
                    playground.food = null
                    playground.stage = null
                    binding.actionButton.text = resources.getString(R.string.quit)
                    binding.gameOverTextView.visibility = View.GONE
                    setScore(0)
                    timer.start()
                } else {
                    music.pause()
                    gameOver = true
                    binding.actionButton.text = resources.getString(R.string.new_game)
                    timer.cancel()
                }
                start = false
            }

            btnLeft.setOnClickListener { playground.left() }
            btnRight.setOnClickListener { playground.right() }
            btnDown.setOnClickListener { playground.down() }
            btnUp.setOnClickListener { playground.up() }
        }

    }

    fun setGameOver() {
        gameOver = true

    }

    fun setScore(score: Int) {
        binding.scoreTextView.text = "Score: ${score}"
    }

    fun playEaten() {
        eating.start()
    }

    override fun onPause() {
        super.onPause()
        music.pause()
    }
}