package screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.borlanddev.notes.R

class SplashFragment: Fragment(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.postDelayed({

            findNavController().navigate(R.id.action_splashFragment_to_noteListFragment3)

        }, 3000)


    }

}