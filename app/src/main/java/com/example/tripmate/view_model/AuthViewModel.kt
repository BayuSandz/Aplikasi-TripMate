package com.example.tripmate.view_model

import androidx.lifecycle.ViewModel
import com.example.tripmate.R
import com.example.tripmate.data.User
import com.example.tripmate.model.Place
import com.example.tripmate.utils.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Response

class AuthViewModel : ViewModel() {

    private val apiService = RetrofitInstance.apiService

    suspend fun registerUser(nama: String, email: String, password: String): Response<ResponseBody> {
        val user = User(nama, email, password)
        return apiService.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): Response<ResponseBody> {
        val user = User("", email, password)
        return apiService.loginUser(user)
    }

    private val places = listOf(
        Place(
            "1",
            "Pantai Kuta",
            "Bali",
            "Rp 10.000",
            "Pantai Kuta merupakan salah satu pantai terkenal di Bali yang terkenal dengan keindahan pasir putihnya dan ombak yang cocok untuk berselancar. Pantai ini juga menawarkan pemandangan matahari terbenam yang spektakuler, menjadikannya tujuan favorit wisatawan dari seluruh dunia.",
            R.drawable.kuta,
            4.5
        ),
        Place(
            "2",
            "Candi Borobudur",
            "Magelang",
            "Rp 30.000",
            "Candi Borobudur adalah candi Buddha terbesar di dunia yang terletak di Magelang, Jawa Tengah. Dibangun pada abad ke-9, candi ini merupakan salah satu keajaiban arsitektur Indonesia dengan ukiran-ukiran yang sangat indah. Candi Borobudur juga menjadi salah satu destinasi wisata religi yang populer.",
            R.drawable.borobudur,
            4.8
        ),
        Place(
            "3",
            "Gunung Bromo",
            "Malang",
            "Rp 20.000",
            "Gunung Bromo adalah salah satu gunung berapi yang masih aktif di Indonesia yang terletak di Taman Nasional Bromo Tengger Semeru, Jawa Timur. Gunung ini terkenal dengan pemandangan kawah yang luas dan panorama matahari terbit yang memukau, menjadikannya tujuan utama para pendaki dan penggemar fotografi.",
            R.drawable.bromo,
            4.2
        ),
        Place(
            "4",
            "Danau Toba",
            "Sumatera Utara",
            "Rp 15.000",
            "Danau Toba adalah danau vulkanik terbesar di dunia yang terletak di Sumatera Utara. Danau ini terbentuk dari letusan supervulkanik ribuan tahun yang lalu dan menjadi rumah bagi pulau Samosir di tengahnya. Selain keindahan alamnya, Danau Toba juga memiliki budaya Batak yang kaya dan unik.",
            R.drawable.medan,
            4.7
        ),
        Place(
            "5",
            "Pulau Komodo",
            "Nusa Tenggara Timur",
            "Rp 25.000",
            "Pulau Komodo terkenal sebagai habitat bagi komodo, reptil terbesar dan terberat di dunia yang hanya dapat ditemukan di Indonesia. Pulau ini juga menawarkan keindahan bawah laut yang menakjubkan dengan terumbu karangnya yang masih alami, ideal untuk snorkeling dan diving.",
            R.drawable.komodo,
            4.9
        ),
        Place(
            "6",
            "Taman Mini Indonesia Indah",
            "Jakarta",
            "Rp 15.000",
            "Taman Mini Indonesia Indah (TMII) adalah taman budaya yang mempersembahkan keanekaragaman budaya Indonesia dalam satu tempat. Di sini, pengunjung dapat menjelajahi replika rumah adat dari berbagai daerah, menonton pertunjukan seni budaya, dan menikmati beragam kuliner tradisional.",
            R.drawable.tmii,
            4.4
        ),
        Place(
            "7",
            "Raja Ampat",
            "Papua Barat",
            "Rp 50.000",
            "Raja Ampat adalah gugusan kepulauan yang terletak di ujung barat Pulau Papua. Kepulauan ini terkenal dengan keanekaragaman hayati bawah lautnya yang melimpah, termasuk terumbu karang warna-warni dan spesies ikan yang langka. Raja Ampat menjadi surganya para penyelam dan penggemar keindahan alam.",
            R.drawable.ampat,
            4.9
        ),
        Place(
            "8",
            "Tanah Lot",
            "Bali",
            "Rp 20.000",
            "Tanah Lot adalah sebuah pura yang terletak di atas batu karang di pesisir barat Bali. Pura ini dianggap sebagai salah satu ikon pariwisata Bali dan sering digunakan sebagai lokasi untuk menikmati pemandangan matahari terbenam yang dramatis.",
            R.drawable.tanah_lot,
            4.6
        ),
        Place(
            "9",
            "Taman Nasional Ujung Kulon",
            "Banten",
            "Rp 25.000",
            "Taman Nasional Ujung Kulon terletak di ujung barat Pulau Jawa, Banten. Taman nasional ini merupakan rumah bagi berbagai spesies satwa langka termasuk badak Jawa, serta menyimpan hutan hujan tropis yang luas.",
            R.drawable.ujung_kulon,
            4.5
        ),
        Place(
            "10",
            "Bukit Lawang",
            "Sumatera Utara",
            "Rp 10.000",
            "Bukit Lawang adalah sebuah desa di Sumatera Utara yang terkenal sebagai pusat rehabilitasi orangutan. Selain menyaksikan orangutan dalam habitatnya, pengunjung juga dapat menikmati keindahan hutan hujan tropis yang masih alami.",
            R.drawable.bukit_lawang,
            4.7
        ),
        Place(
            "11",
            "Taman Nasional Bromo Tengger Semeru",
            "Jawa Timur",
            "Rp 25.000",
            "Taman Nasional Bromo Tengger Semeru adalah taman nasional yang terletak di Jawa Timur. Taman nasional ini terkenal dengan pemandangan alamnya yang dramatis, termasuk gunung berapi Bromo, kawahnya yang luas, dan Gunung Semeru yang menjulang tinggi.",
            R.drawable.taman_bromo,
            4.8
        ),
        Place(
            "12",
            "Gili Trawangan",
            "Lombok",
            "Rp 15.000",
            "Gili Trawangan adalah pulau terbesar dan paling populer di antara tiga pulau Gili di Lombok. Pulau ini terkenal dengan pantai berpasir putihnya, kehidupan malam yang ramai, serta aktivitas snorkeling dan diving di sekitar terumbu karangnya yang indah.",
            R.drawable.gili_trawangan,
            4.6
        ),
        Place(
            "13",
            "Pulau Weh",
            "Aceh",
            "Rp 20.000",
            "Pulau Weh adalah sebuah pulau kecil yang terletak di ujung barat laut Sumatera. Pulau ini dikenal sebagai surga bagi pecinta diving berkat keindahan terumbu karangnya yang masih terjaga dan keanekaragaman hayati bawah laut yang melimpah.",
            R.drawable.pulau_weh,
            4.5
        ),
        Place(
            "14",
            "Pantai Parangtritis",
            "Yogyakarta",
            "Rp 10.000",
            "Pantai Parangtritis adalah pantai paling terkenal di Yogyakarta yang menawarkan pemandangan pantai yang luas dengan pasir hitamnya yang halus. Pantai ini juga memiliki nilai sejarah dan legenda mistis yang kuat di masyarakat setempat.",
            R.drawable.parangtritis,
            4.3
        ),
        Place(
            "15",
            "Kawah Ijen",
            "Banyuwangi",
            "Rp 30.000",
            "Kawah Ijen adalah kawah dari gunung berapi kompleks Ijen yang terletak di Banyuwangi, Jawa Timur. Kawah ini terkenal dengan fenomena blue fire-nya yang hanya dapat dilihat pada malam hari, serta pemandangan danau kawah berwarna hijau toska yang memukau.",
            R.drawable.kawah_ijen,
            4.7
        ),
        Place(
            "16",
            "Puncak Jayawijaya",
            "Papua",
            "Rp 50.000",
            "Puncak Jayawijaya adalah puncak tertinggi di Indonesia yang terletak di Provinsi Papua. Puncak ini menjadi tujuan para pendaki gunung dan peneliti alam karena keindahan alamnya yang menakjubkan serta pentingnya sebagai bagian dari ekosistem alam Papua.",
            R.drawable.jayawijaya,
            4.9
        ),
        Place(
            "17",
            "Tana Toraja",
            "Sulawesi Selatan",
            "Rp 25.000",
            "Tana Toraja adalah sebuah wilayah di Sulawesi Selatan yang terkenal dengan kebudayaan dan adat istiadatnya yang khas. Salah satu daya tarik utama Tana Toraja adalah pemakaman tradisionalnya yang unik dan rumah adat Tongkonan yang memiliki arsitektur tradisional yang indah.",
            R.drawable.tana_toraja,
            4.4
        ),
        Place(
            "18",
            "Taman Sari",
            "Yogyakarta",
            "Rp 15.000",
            "Taman Sari adalah kompleks istana air yang terletak di Yogyakarta. Dibangun pada abad ke-18, Taman Sari dikenal karena arsitektur Jawa klasiknya dan sejarahnya sebagai tempat rekreasi para sultan Yogyakarta dan keluarganya.",
            R.drawable.taman_sari,
            4.5
        ),
        Place(
            "19",
            "Labuan Bajo",
            "Nusa Tenggara Timur",
            "Rp 30.000",
            "Labuan Bajo adalah kota kecil yang terletak di barat Pulau Flores, Nusa Tenggara Timur. Kota ini dikenal sebagai gerbang menuju Taman Nasional Komodo, dan menjadi basis bagi para pelancong untuk menjelajahi keindahan alamnya yang mempesona, termasuk Pulau Rinca dan Pulau Padar.",
            R.drawable.labuan_bajo,
            4.6
        ),
        Place(
            "20",
            "Pantai Pink",
            "Lombok",
            "Rp 15.000",
            "Pantai Pink adalah salah satu pantai langka di dunia yang terletak di Lombok. Pasir pantai ini memiliki warna yang unik, yaitu pink alami yang berasal dari serpihan karang merah yang terhanyut. Pantai ini menawarkan pemandangan laut yang indah dan kehidupan bawah laut yang kaya.",
            R.drawable.pantai_pink,
            4.4
        ),
        Place(
            "21",
            "Pantai Wediombo",
            "Gunung Kidul",
            "Rp 10.000",
            "Pantai Wediombo terletak di Kabupaten Gunung Kidul, Yogyakarta. Pantai ini terkenal dengan panorama alamnya yang masih alami, ombak yang cocok untuk surfing, serta tebing-tebing karang yang menjulang tinggi di sekitarnya.",
            R.drawable.wediombo,
            4.3
        ),
        Place(
            "22",
            "Pulau Seribu",
            "Jakarta",
            "Rp 50.000",
            "Pulau Seribu adalah gugusan kepulauan di utara Jakarta yang terkenal dengan keindahan pantainya dan keanekaragaman ekosistem bawah lautnya. Pulau-pulau ini menjadi tujuan populer untuk berlibur dan snorkeling di sekitar terumbu karangnya.",
            R.drawable.pulau_seribu,
            4.5
        ),
        Place(
            "23",
            "Pantai Sanur",
            "Bali",
            "Rp 20.000",
            "Pantai Sanur adalah salah satu pantai terkenal di Bali yang terletak di sebelah timur kota Denpasar. Pantai ini terkenal dengan suasana tenangnya, ombak yang tenang cocok untuk berenang, dan menjadi lokasi favorit untuk menikmati matahari terbit.",
            R.drawable.pantai_sanur,
            4.6
        ),
        Place(
            "24",
            "Pulau Morotai",
            "Maluku Utara",
            "Rp 25.000",
            "Pulau Morotai adalah sebuah pulau yang terletak di utara Maluku, Indonesia. Pulau ini terkenal dengan keindahan alamnya yang masih alami, terumbu karangnya yang indah, serta sebagai lokasi bersejarah dari Perang Dunia II.",
            R.drawable.morotai,
            4.3
        ),
        Place(
            "25",
            "Pantai Teluk Asmara",
            "Pacitan",
            "Rp 10.000",
            "Pantai Teluk Asmara terletak di Kabupaten Pacitan, Jawa Timur. Pantai ini terkenal dengan keindahan alamnya yang memesona, ombak yang cukup besar cocok untuk surfing, serta tebing-tebing karang yang menjulang tinggi di sekitarnya.",
            R.drawable.pacitan,
            4.2
        )
    )

    fun getAllPlaces(): List<Place> {
        return places
    }

    fun searchPlaces(query: String?): List<Place> {
        if (query.isNullOrEmpty()) {
            return places
        }
        return places.filter {
            it.nama_wisata.contains(query, ignoreCase = true) ||
                    it.kota.contains(query, ignoreCase = true)
        }
    }
}
