package br.well.movies.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import br.well.coreapp.ext.loadImage
import br.well.coreapp.ext.toHumanDate
import br.well.movies.R
import br.well.movies.ui.view.adapter.model.MovieItemAdapter
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
    private val items: ArrayList<MovieItemAdapter>,
    private val listener: Listener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingAdded: Boolean = false

    interface Listener {
        fun onMovieClicked(movieId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewHolderType.LOADING.typeId -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_progess, parent, false)
                LoadingViewHolder(view)
            }
            ViewHolderType.ITEM.typeId -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(view)
            }
            else -> throw IllegalArgumentException("You must pass viewType as LOADING or ITEM")
        }
    }

    @CallSuper
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LoadingViewHolder -> {
                holder.bind()
            }
            is MovieViewHolder -> {
                with(holder.itemView) {
                    holder.itemView.movieCardViewGroup.setOnClickListener {
                        listener.onMovieClicked(items[position].id)
                    }
                }
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position == items.size - 1 && isLoadingAdded) {
            true -> ViewHolderType.LOADING.typeId
            false -> ViewHolderType.ITEM.typeId
        }
    }

    open fun addAll(moviesItemAdapter: ArrayList<MovieItemAdapter>) {
        val size = this.items.size
        size + moviesItemAdapter.size
        with(this.items) {
            addAll(moviesItemAdapter)
        }
        notifyDataSetChanged()
    }

    fun showLoading() {
        isLoadingAdded = true
        notifyDataSetChanged()
    }

    fun hideLoading() {
        isLoadingAdded = false
        val position = items.size - 1
        notifyItemChanged(position)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieItemAdapter) {
            with(itemView) {
                movieImageView.loadImage(item.movieCover)
                movieNameView.text = item.movieName
                movieRateView.text = item.movieRating.toString()
                movieReleaseDateView.text = item.releaseDate.toHumanDate()
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

}

enum class ViewHolderType(val typeId: Int) {
    LOADING(0),
    ITEM(1)
}