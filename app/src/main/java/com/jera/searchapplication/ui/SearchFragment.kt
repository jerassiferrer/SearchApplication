package com.jera.searchapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jera.searchapplication.R
import com.jera.searchapplication.data.DataSource
import com.jera.searchapplication.domain.RepoImpl
import com.jera.searchapplication.data.model.Track
import com.jera.searchapplication.databinding.FragmentSearchBinding
import com.jera.searchapplication.valueobject.Resource
import com.jera.searchapplication.viewmodel.SearchViewModel
import com.jera.searchapplication.viewmodel.ViewModelFactory


/**
 *
 * SearchFragment shows edit text and button to load a list of items.
 *
 */
class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel>{ ViewModelFactory(RepoImpl(DataSource())) }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSearchView()
        setUpObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpSearchView(){
        binding.enterArtistEt.queryHint = getString(R.string.enter_artist)
        binding.enterArtistEt.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query.isNullOrBlank()){
                    Toast.makeText( requireContext(),getText(R.string.empty_text), Toast.LENGTH_SHORT).show()
                }else {
                    query?.let { viewModel.setSearchData(it) }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        binding.enterArtistBtn.setOnClickListener {
            viewModel.setSearchData(binding.enterArtistEt.query.toString())
        }
    }

    private fun setUpRecyclerView(){
        binding.searchRv.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        viewModel.fetchTracksList.observe(viewLifecycleOwner, Observer { result ->
            Log.d("SearchFragment", "result++++++:$result")
            when (result) {
                is Resource.Loading -> {
                    binding.searchProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.searchProgressBar.visibility = View.GONE
                    binding.searchRv.adapter = SearchAdapter(requireContext(), result.data as List<Track>)
                }
                is Resource.Failure -> {
                    binding.searchProgressBar.visibility= View.GONE
                    Toast.makeText( requireContext(),getText(R.string.error_loading), Toast.LENGTH_SHORT).show()
                    Log.d("SearchFragment", "error loading ${result.exception}")
                }
            }
        })
    }

    private fun setUpObservers(){

    }

}