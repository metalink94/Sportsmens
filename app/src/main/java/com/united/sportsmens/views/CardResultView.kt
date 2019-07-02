package com.united.sportsmens.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.united.sportsmens.R
import com.united.sportsmens.models.ResultModel
import com.united.sportsmens.models.Team
import kotlinx.android.synthetic.main.card_result_item.view.*

class CardResultView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(getContext(), R.layout.card_result_item, this)
    }

    fun initModel(model: ResultModel) {
        setLeftSide(model.leftTeam)
        setRightSide(model.rightTeam)
        setScore(model.score)
    }

    private fun setScore(scoreText: String) {
        score.text = scoreText
    }

    private fun setRightSide(rightTeam: Team) {
        setRightIcon(rightTeam.iconRes)
        setRightTitle(rightTeam.teamName)
        setRightCoef(rightTeam.teamCoef)
    }

    private fun setRightCoef(teamCoef: String) {
        rightCoef.text = teamCoef
    }

    private fun setRightTitle(teamName: String) {
        rightTitle.text = teamName
    }

    private fun setRightIcon(iconRes: Int) {
        rightIcon.setImageResource(iconRes)
    }

    private fun setLeftSide(leftTeam: Team) {
        setLeftIcon(leftTeam.iconRes)
        setLeftTitle(leftTeam.teamName)
        setLeftCoef(leftTeam.teamCoef)
    }

    private fun setLeftCoef(teamCoef: String) {
        leftCoef.text = teamCoef
    }

    private fun setLeftTitle(teamName: String) {
        leftTitle.text = teamName
    }

    private fun setLeftIcon(iconRes: Int) {
        leftIcon.setImageResource(iconRes)
    }
}
