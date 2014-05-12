package org.imie;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		Button myButton = new Button(shell,SWT.NONE);
		myButton.setText("i->i+1");
		
		Composite composite = new Composite(shell, SWT.CENTER);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
		
		Label myLabel = new Label(composite, SWT.CENTER);
		myLabel.setText("i = 0");
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
