package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.PokeshopBuyFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokeshopBuyView
import com.example.lpiem.pokecard.presentation.ui.adapter.BuyPokemonAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokeshop_buy.*
import javax.inject.Inject

class PokeshopBuyFragment : BaseFragment<PokeshopBuyFragmentPresenter>(), PokeshopBuyView {
    override val layoutId: Int = R.layout.fragment_pokeshop_buy
    val animals: ArrayList<String> = ArrayList()


    @Inject
    override lateinit var presenter: PokeshopBuyFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)

        buyList.layoutManager = LinearLayoutManager(context)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)
        addAnimals()
        buyList.adapter = BuyPokemonAdapter(animals, context)

    }

    fun addAnimals() {
        animals.add("dog")
        animals.add("cat")
        animals.add("owl")
        animals.add("cheetah")
        animals.add("raccoon")
        animals.add("bird")
        animals.add("snake")
        animals.add("lizard")
        animals.add("hamster")
        animals.add("bear")
        animals.add("lion")
        animals.add("tiger")
        animals.add("horse")
        animals.add("frog")
        animals.add("fish")
        animals.add("shark")
        animals.add("turtle")
        animals.add("elephant")
        animals.add("cow")
        animals.add("beaver")
        animals.add("bison")
        animals.add("porcupine")
        animals.add("rat")
        animals.add("mouse")
        animals.add("goose")
        animals.add("deer")
        animals.add("fox")
        animals.add("moose")
        animals.add("buffalo")
        animals.add("monkey")
        animals.add("penguin")
        animals.add("parrot")
    }


}