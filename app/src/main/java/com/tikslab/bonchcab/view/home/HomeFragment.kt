package com.tikslab.bonchcab.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tikslab.bonchcab.R
import com.tikslab.bonchcab.presenter.TablePresenter
import com.tikslab.bonchcab.view.TableAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TablePresenter.init(this)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTableView()
        TablePresenter.onStart()

        prevButton.setOnClickListener { TablePresenter.onPrevWeek() }
        nextButton.setOnClickListener { TablePresenter.onNextWeek() }
        updateWeek()

    }

    private fun createTableView() {
        val tableAdapter = TableAdapter()
        val manager = LinearLayoutManager(context)

        tableView.apply {
            adapter = tableAdapter
            layoutManager = manager
        }
    }

    fun updateWeek(){
        prevButton.text = (TablePresenter.currWeek - 1).toString()
        nextButton.text = (TablePresenter.currWeek + 1).toString()
        weekNumText.text = TablePresenter.currWeek.toString()
        tableView.adapter?.notifyDataSetChanged()


    }

    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
        tableView.visibility = View.GONE
    }

    fun hideProgressBar(){
        progressBar.visibility = View.GONE
        tableView.visibility = View.VISIBLE
    }
}