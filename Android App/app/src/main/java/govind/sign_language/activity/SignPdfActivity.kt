package govind.sign_language.activity

import android.graphics.Canvas
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MotionEvent
import android.widget.ProgressBar
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.*
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
                            .onDraw(object: OnDrawListener {
                                override fun onLayerDrawn(canvas: Canvas, pageWidth:Float, pageHeight:Float, displayedPage:Int) {
                                }
                            })
                            .onDrawAll(object: OnDrawListener {
                                override fun onLayerDrawn(canvas:Canvas, pageWidth:Float, pageHeight:Float, displayedPage:Int) {
                                }
                            })
                            .onPageError(object: OnPageErrorListener {
                                override fun onPageError(page:Int, t:Throwable) {
                                    Toast.makeText(this@SignPdfActivity, "Error", Toast.LENGTH_LONG).show()
                                }
                            })
                            .onPageChange(object: OnPageChangeListener {
                                override fun onPageChanged(page:Int, pageCount:Int) {
                                }
                            })
                            .onTap(object: OnTapListener {
                                override fun onTap(e: MotionEvent):Boolean {
                                    return true
                                }
                            })
                            .onRender(object: OnRenderListener {
                                override fun onInitiallyRendered(nbPages:Int, pageWidth:Float, pageHeight:Float) {
                                    pdfView.fitToWidth()
                                }
                            })
                            .enableAnnotationRendering(true)
                            .invalidPageColor(Color.WHITE)
                            .load()
                }
            }
        }
    }
}
