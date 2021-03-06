package com.tikslab.bonchcab.presenter

import com.tikslab.bonchcab.model.network.RestService
import com.tikslab.bonchcab.model.Util
import com.tikslab.bonchcab.model.pojo.DayOfWeek
import com.tikslab.bonchcab.model.pojo.WeekTable
import com.tikslab.bonchcab.view.timetable.TimeTableFragment

object TablePresenter {
    lateinit var view: TimeTableFragment
    var currWeek = Util.getWeek()
    var weekTable = Util.getErrorTable("Load")

    fun init(view: TimeTableFragment) {
        this.view = view
    }

    private fun loadNextWeek() {
        currWeek++
        view.showProgressBar()
        RestService.getRaspWithWeek(currWeek, AuthPresenter.email, AuthPresenter.pass)
    }

    private fun loadCurrWeek() {
        view.showProgressBar()
        RestService.getRaspWithWeek(currWeek, AuthPresenter.email, AuthPresenter.pass)
    }

    private fun loadPrevWeek() {
        currWeek--
        view.showProgressBar()
        RestService.getRaspWithWeek(currWeek, AuthPresenter.email, AuthPresenter.pass)
    }

    fun updateTable(table: WeekTable) {
        weekTable = table
        view.updateWeek()
        view.hideProgressBar()
    }

    fun onPrevWeek() {
        loadPrevWeek()
        view.updateWeek()

    }

    fun onNextWeek() {
        loadNextWeek()
        view.updateWeek()

    }

    fun onStart() {
        loadCurrWeek()
        view.updateWeek()
    }

    fun getCurrDayOfWeek(): DayOfWeek {
        return Util.getCurrDay()
    }


}