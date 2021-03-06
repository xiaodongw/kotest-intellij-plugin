package io.kotest.plugin.intellij.toolwindow

import com.intellij.psi.NavigatablePsiElement
import javax.swing.event.TreeSelectionEvent
import javax.swing.event.TreeSelectionListener

object TestExplorerTreeSelectionListener : TreeSelectionListener {

   override fun valueChanged(e: TreeSelectionEvent) {
      if (TestExplorerState.autoscrollToSource) {
         val psi = when (val node = e.path.nodeDescriptor()) {
            is SpecNodeDescriptor -> node.psi
            is CallbackNodeDescriptor -> node.psi
            is TestNodeDescriptor -> node.psi
            is IncludeNodeDescriptor -> node.include.psi
            else -> null
         }

         when (psi) {
            is NavigatablePsiElement -> psi.navigate(false)
         }
      }
   }
}
