package com.borlanddev.notes.controller


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.notes.R
import com.borlanddev.notes.model.NoteListViewModel
import com.borlanddev.notes.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

private const val TAG = "NoteListFragment"
private const val ARG_NOTE_ID = "noteId"

class NoteListFragment: Fragment(R.layout.fragment_list_note) {

    private lateinit var note: Note

    private var adapter: NoteAdapter? = NoteAdapter(emptyList())
    // Получаем ссылку на вью модель
    private val noteListViewModel: NoteListViewModel by lazy {
        ViewModelProviders.of(this).get(NoteListViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fab = view.findViewById(R.id.new_note_FAB) as FloatingActionButton

        /*Определяем как отображать элементы в ресайклере и как работает прокрутка,
        с помощью специальных менеджеров */
        val noteRecyclerView = view.findViewById(R.id.note_recycler_view) as RecyclerView

        noteRecyclerView.layoutManager = LinearLayoutManager(context).apply {
            stackFromEnd = true   // Новый элемент списка добавляется вверху
            reverseLayout = true
        }

        noteRecyclerView.adapter = adapter



      //val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailsFragment2(noteId)


        //**********************************************************************************************

        /* Функция регестрирует наблюдателя за экземпляром LiveData и связи наблюдателя с
           жизненным циклом другого компонента */

        noteListViewModel.noteListLiveData.observe(

            viewLifecycleOwner
        )   /* Первый параметр функции observe - Владелец ЖЦ.
            (наблюдатель будет (жить) получать обновления данных столько,сколько живет Fragment) */

        /* Второй параметр, реализация Observer - наблюдатель (преобразовали SAM в лямбду).
                Он отвечает за реакцию на новые данные из LiveData. Блок кода выполняется всякий раз
                    когда обновляется список в LiveData */
        { notes ->
            notes?.let {

                /* Когда все виджеты будут готовы и отрисованы на экране и выполнятся запросы из БД,
                         можно обновлять интерфейс. */

                // Update IU
                adapter = NoteAdapter(notes)
                noteRecyclerView.adapter = adapter
            }
        }


        fab.setOnClickListener {

            note = Note().apply {
                title = "Новая заметка"
                description = ""
            //  date = "Текущая дата"

            }

            noteListViewModel.newNote(note)
        }
    }








    // Холдер - ячейка (визуальный элемент списка, контейнер для наших данных)
    private inner class NoteHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var note: Note

        private val titleNote: TextView = itemView.findViewById(R.id.item_title_note)
        private val descriptionNote: TextView = itemView.findViewById(R.id.item_description_note)

        private val dateNote: TextView = itemView.findViewById(R.id.item_date_note)
        // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ ДАТЫ !!!!!!!!!!!!!!!!!!!

        init { // ставим слушателя на каждую вьюшку вьюХолдера
            itemView.setOnClickListener (this)
        }

        fun bind(note: Note) {
            this.note = note

            titleNote.text = this.note.title
            descriptionNote.text = this.note.description
            dateNote.text = this.note.date         // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ ДАТЫ !!!!!!!!!!!!!!!!!!!

        }

        override fun onClick(v: View?) {

            val bundle = Bundle()
            val noteId = note.id

            // Передаем айдишник выбранной заметки
            bundle.putSerializable("noteId", noteId)
            findNavController().navigate(R.id.action_noteListFragment_to_noteDetailsFragment2, bundle)

            // Временное уведомление
            Toast.makeText(context, "Заметка: ${ (note.title) } ", Toast.LENGTH_SHORT ).show()
            }


        }




    private inner class NoteAdapter (var notes: List<Note>)
        : RecyclerView.Adapter<NoteHolder>() {

        // функция отвечает за создание вьюХолдера на дисплее
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

            // заполняем list_item_note и оборачиваем эту вью в вьюХолдер
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_note, parent, false)

            return NoteHolder(view)
        }

        // в этой функции должно быть минимум вычислений для более плавной прокрутки интерфеса
        // отвечает за заполнение данного холдера заметкой из данной позиции (индекс списка заметок)
        override fun onBindViewHolder(holder: NoteHolder, position: Int) {
            val note = notes[position]

            // заполняем поля по каждой позиции холдера в списке (связываем данные с вьюХолдером)
            holder.bind(note)
        }
        // утилизатор узнает заранее сколько элементов ему нужно будет отобразить
        override fun getItemCount() = notes.size
    }



    companion object { // Инкапуслируем получение нашего фрагмента , для активити и пр.

        const val splashCreate = "Created splash screen"

    }
}






    /*
        // Удаление элементов списка смахиванием
        val swipeToDeleteCallBack = object : SwipeToDeleteCallback() {
            val listNotes = noteListViewModel.notes     //noteListLiveData

            // Функция обнаруживает свайпы
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // Определяем индекс элемента по которому был свайп
                val position = viewHolder.adapterPosition

                // !!!!!!!!!!!!!!!!! поведение удаления элемента !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                // listNotes.removeAt(position)

                /* Нужно убедиться, что элемент больше не отображается.
                 При этом также запускается анимация удаления элемента по умолчанию */
                noteRecyclerView.adapter?.notifyItemRemoved(position)
            }
        }
            // Прикрепляем к ItemTouchHelper наш Recycler
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
            itemTouchHelper.attachToRecyclerView(noteRecyclerView)

     */


    /*

    private fun updateUIList(notes: List<Note>) {

        adapter = NoteAdapter(notes)
        noteRecyclerView.adapter = adapter
    }

    */



