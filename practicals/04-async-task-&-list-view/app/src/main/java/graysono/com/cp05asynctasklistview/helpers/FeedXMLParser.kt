package graysono.com.cp05asynctasklistview.helpers

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class FeedXMLParser {
    var feedEntries = ArrayList<FeedEntry>()

    fun parse(data: String): Boolean {
        var isValid = true
        var isInEntryTag = false
        var txtVal = ""

        try {
            val xmlPPFactory: XmlPullParserFactory = XmlPullParserFactory.newInstance()
            xmlPPFactory.isNamespaceAware = true
            val newPullParser: XmlPullParser = xmlPPFactory.newPullParser()
            newPullParser.setInput(data.reader())
            var eventType: Int = newPullParser.eventType
            var feedEntry = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName: String? = newPullParser.name?.toLowerCase()
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagName == "entry") isInEntryTag = true
                    XmlPullParser.TEXT -> txtVal = newPullParser.text
                    XmlPullParser.END_TAG -> {
                        if (isInEntryTag) {
                            when (tagName) {
                                "entry" -> {
                                    feedEntries.add((feedEntry))
                                    isInEntryTag = false
                                    feedEntry = FeedEntry()
                                }
                                "name" -> feedEntry.name = txtVal
                                "releasedate" -> feedEntry.releaseDate = txtVal
                            }
                        }
                    }
                }
                eventType = newPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            isValid = false
        }
        return isValid
    }
}


