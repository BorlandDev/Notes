package com.borlanddev.notes.controller


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.notes.R
import com.borlanddev.notes.SwipeToDeleteCallback
import com.borlanddev.notes.model.ListViewModel
import com.borlanddev.notes.model.Note

private const val TAG = "ListFragment"

class ListFragment: Fragment()  {

    // Получаем ссылку на вью модель
    private val listViewModel: ListViewModel by lazy {
        ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

    private lateinit var noteRecyclerView: RecyclerView
    private var adapter: NoteAdapter? = null



    // аналог setContentView, настраивает и возвращает готовую верстку экрана
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        /*Определяем как отображать элементы в ресайклере и как работает прокрутка,
         с помощью специальных менеджеров */
        noteRecyclerView = view.findViewById(R.id.note_recycler_view) as RecyclerView
        noteRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeToDeleteCallBack = object : SwipeToDeleteCallback() {

            val listNotes = listViewModel.notes

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                listNotes.removeAt(position)
                noteRecyclerView.adapter?.notifyItemRemoved(position)

            }
        }

            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
            itemTouchHelper.attachToRecyclerView(noteRecyclerView)
        }








    // Холдер - ячейка (визуальный элемент списка, контейнер для наших данных)
    private inner class NoteHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

         lateinit var note: Note

         val titleNote: TextView = itemView.findViewById(R.id.item_title_note)
         val descriptionNote: TextView = itemView.findViewById(R.id.item_description_note)
         val dateNote: TextView = itemView.findViewById(R.id.item_date_note)

        init { // ставим слушателя на каждую вьюшку вьюХолдера
            itemView.setOnClickListener (this)
        }


        fun bind (note: Note) {
            this.note = note

            // Холдер обновляет заголовок и дату соответствующего преступления
            titleNote.text = note.title
            descriptionNote.text = note.description
            dateNote.text = note.date.toString()
        }

        override fun onClick (v: View) {
            /* Уведомляем нашу хост-актиити через интерфейс обратного вызова,
            о том какая заметка была выбрана в списке */

        Toast.makeText(context, "${note.title} pressed!", Toast.LENGTH_SHORT).show()

        }
    }


    private inner class NoteAdapter (var notes: List<Note>)
        : RecyclerView.Adapter<NoteHolder>() {

        // функция отвечает за создание вьюХолдера на дисплее
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : NoteHolder {

            // заполняем list_item_note и оборачиваем эту вью в вьюХолдер
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_note, parent, false)

            return NoteHolder(view)
        }

        // утилизатор узнает заранее сколько элементов ему нужно будет отобразить
        override fun getItemCount() = notes.size


        // в этой функции должно быть минимум вычислений для более плавной прокрутки интерфеса
        // отвечает за заполнение данного холдера заметкой из данной позиции (индекс списка заметок)
        override fun onBindViewHolder(holder: NoteHolder, position: Int) {

            val note = notes[position]

            // заполняем поля по каждой позиции холдера в списке (связываем данные с вьюХолдером)
            holder.bind(note)
            }
        }



    private fun updateUI() {
        val notes = listViewModel.notes

        adapter = NoteAdapter(notes)
        noteRecyclerView.adapter = adapter

    }




    companion object { // Инкапуслируем получение нашего фрагмента , для активити и пр.
        fun newInstance() = ListFragment()
    }

}