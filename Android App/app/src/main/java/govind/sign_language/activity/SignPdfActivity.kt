package govind.sign_language.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.ProgressBar
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView
import govind.sign_language.R

class SignPdfActivity : AppCompatActivity() {

    lateinit var pdfView: PDFView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_sign_pdf)
        pdfView = findViewById(R.id.pdf_viewer)
        progressBar = findViewById(R.id.progress_bar)
        if (intent != null)
        {
            val viewType = intent.getStringExtra("ViewType")
            if (viewType != null || TextUtils.isEmpty(viewType))
            {
                if (viewType == "assets")
                {
                    pdfView.fromAsset("Alphabets.pdf")
                            .password(null)//enter password if PDF is password protected
                            .defaultPage(0)//set the default page
                            .enableSwipe(true)//enable the swipe to change page
                            .swipeHorizontal(false)//set horizontal swipe to false
                            .enableDoubletap(true)//double tap to zoom
                            .onDraw { canvas, pageWidth, pageHeight, displayedPage -> }
                            .onDrawAll { canvas, pageWidth, pageHeight, displayedPage -> }
                            .onPageError { page, t -> Toast.makeText(this@SignPdfActivity, "Error", Toast.LENGTH_LONG).show() }
                            .onPageChange { page, pageCount -> }
                            .onTap { true }
                            .onRender { nbPages, pageWidth, pageHeight -> pdfView.fitToWidth() }
                            .enableAnnotationRendering(true)
                            .invalidPageColor(Color.WHITE)
                            .load()
                }
            }
        }
    }
}
