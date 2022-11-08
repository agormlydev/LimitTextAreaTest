package com.purefishing.qc.client.ui.utilities

import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JTextArea
import javax.swing.text.AttributeSet
import javax.swing.text.PlainDocument

internal class PlainDocumentWithCharLimit(limit: Int) : PlainDocument() {
    private val limit: Int

    init {
        require(limit > 0) { "Limit can not be <= 0" }
        this.limit = limit
    }

    override fun insertString(offset: Int, str: String?, attr: AttributeSet?) {
        if (str.isNullOrEmpty()) return
        val canAdd = limit - length
        val strToActuallyAdd = str.substring(0, if (canAdd < str.length) canAdd else str.length)
        super.insertString(offset, strToActuallyAdd, attr)
    }
}


fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    JFrame().apply {
        add(JTextArea().apply {
            document = PlainDocumentWithCharLimit(3)
        })
        isVisible = true
        size = Dimension(800, 200)
        setLocationRelativeTo(null)
    }
}