package op.mobile.app.dev.companies

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader

class CompanyJSONReader(private val context: Context) {
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.data)

    fun read(): List<Company> {
        val itemType = object : TypeToken<List<CompanyJSONResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson<List<CompanyJSONResponse>>(reader, itemType).map {
            it.toCompany()
        }
    }
}
