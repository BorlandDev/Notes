package com.borlanddev.notes.controller


import android.content.Context
import android.os.Bundle
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
import com.borlanddev.notes.helpers.SwipeToDeleteCallback
import com.borlanddev.notes.model.NoteListViewModel
import com.borlanddev.notes.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class NoteListFragment: Fragment() {



    // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ НАВИГАЦИИ
    interface Callbacks {
        fun onNoteSelected(noteId: UUID)
    }

    // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ НАВИГАЦИИ
    private var callbacks: Callbacks? = null




    private lateinit var note: Note
    private lateinit var fab: FloatingActionButton
    private lateinit var noteRecyclerView: RecyclerView
    private var adapter: NoteAdapter? = NoteAdapter(emptyList())

    // Получаем ссылку на вью модель
    private val noteListViewModel: NoteListViewModel by lazy {
        ViewModelProviders.of(this).get(NoteListViewModel::class.java)
    }





    override fun onAttach(context: Context) {
        super.onAttach(context)

        // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ НАВИГАЦИИ
        callbacks = context as Callbacks?
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    // аналог setContentView, настраивает и возвращает готовую верстку экрана
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_note, container, false)

        /*Определяем как отображать элементы в ресайклере и как работает прокрутка,
         с помощью специальных менеджеров */
        noteRecyclerView = view.findViewById(R.id.note_recycler_view) as RecyclerView
        noteRecyclerView.layoutManager = LinearLayoutManager(context)

        noteRecyclerView.adapter = adapter


        fab = view.findViewById(R.id.new_note_FAB)

        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    //**********************************************************************************************


        // Удаление элементов списка смахиванием
        val swipeToDeleteCallBack = object : SwipeToDeleteCallback() {
            val listNotes = noteListViewModel.noteListLiveData


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



    //**********************************************************************************************

        /* Функция регестрирует наблюдателя за экземпляром LiveData и связи наблюдателя с
           жизненным циклом другого компонента */

        noteListViewModel.noteListLiveData.observe (

                viewLifecycleOwner)   /* Первый параметр функции observe - Владелец ЖЦ.
            (наблюдатель будет (жить) получать обновления данных столько,сколько живет Fragment) */

    /* Второй параметр, реализация Observer - наблюдатель (преобразовали SAM в лямбду).
            Он отвечает за реакцию на новые данные из LiveData. Блок кода выполняется всякий раз
                когда обновляется список в LiveData */
            { notes ->
                notes?.let {

    /* Когда все виджеты будут готовы и отрисованы на экране и выполнятся запросы из БД,
             можно обновлять интерфейс. */
                    updateUIList(notes)
            }
        }
    //**********************************************************************************************

    fab.setOnClickListener {
        note = Note()

        noteListViewModel.newNote(note) }


    }




    private fun updateUIList(notes: List<Note>) {

        adapter = NoteAdapter(notes)
        noteRecyclerView.adapter = adapter
    }



    override fun onDetach() {
        super.onDetach()

        // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ НАВИГАЦИИ
        callbacks = null
    }






    // Холдер - ячейка (визуальный элемент списка, контейнер для наших данных)
    private inner class NoteHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

         private lateinit var note: Note

         private val titleNote: TextView = itemView.findViewById(R.id.item_title_note)
         private val descriptionNote: TextView = itemView.findViewById(R.id.item_description_note)
         private val dateNote: TextView = itemView.findViewById(R.id.item_date_note)

        init { // ставим слушателя на каждую вьюшку вьюХолдера
            itemView.setOnClickListener (this)
        }

        fun bind (note: Note) {
            this.note = note

            // Холдер обновляет поля
            titleNote.text = note.title
            descriptionNote.text = note.description
            dateNote.text = note.date.toString()   // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ ДАТЫ !!!!!!!!!!!!!!!!!!!
        }

        override fun onClick (v: View) {
            /* Уведомляем нашу хост-актиити через интерфейс обратного вызова,
            о том какая заметка была выбрана в списке */

            // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ НАВИГАЦИИ
            callbacks?.onNoteSelected(note.id)

            Toast.makeText(context, "Заметка #${ (note.id) } ", Toast.LENGTH_SHORT ).show()

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










    companion object { // Инкапуслируем получение нашего фрагмента , для активити и пр.
        fun newInstance() = NoteListFragment()
    }

}