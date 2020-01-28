package calculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

	@FXML
	private Label resultLabel;
	@FXML
	private TextField expression;
	@FXML
	private Button dn, d0, dzar, d1, d2, d3, d4, d5, d6, d7, d8, d9, drez, dsab, dod, dmn, ddj, dback, ddel, brackl,
			brackr, pow, sqrt;

	private int textPos = 0, textPos1 = 0;

	private Map<Integer, String> operators;
	private Map<Integer, Double> operands;
	private Map<Integer, String> resultOperators;
	private Map<Integer, Double> resultOperands;

	private String covertedExpression;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		resultLabel.setAlignment(Pos.CENTER);
		resultLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-border-width: 1;\n"
				+ "-fx-border-color: darkgray; -fx-line-spacing: 4;");
		drez.setStyle("-fx-text-fill: white; -fx-background-color: limegreen; -fx-border-width: 1;\n"
				+ "-fx-border-radius: 1; fx-border-color: darkgray;");

		expression.setStyle("-fx-alignment: baseline-right; -fx-background-color: ivory; -fx-border-width: 1;\n"
				+ "-fx-border-color: darkgray; -fx-border-radius: 1; -fx-font-size: 20;");

		/* Unos brojeva sa dugmica */
		d0.setOnMouseClicked((MouseEvent klik0) -> {
			numberEvent(d0, klik0);
		});

		d1.setOnMouseClicked((MouseEvent klik1) -> {
			numberEvent(d1, klik1);
		});

		d2.setOnMouseClicked((MouseEvent klik2) -> {
			numberEvent(d2, klik2);
		});

		d3.setOnMouseClicked((MouseEvent klik3) -> {
			numberEvent(d3, klik3);
		});

		d4.setOnMouseClicked((MouseEvent klik4) -> {
			numberEvent(d4, klik4);
		});

		d5.setOnMouseClicked((MouseEvent klik5) -> {
			numberEvent(d5, klik5);
		});

		d6.setOnMouseClicked((MouseEvent klik6) -> {
			numberEvent(d6, klik6);
		});

		d7.setOnMouseClicked((MouseEvent klik7) -> {
			numberEvent(d7, klik7);
		});

		d8.setOnMouseClicked((MouseEvent klik8) -> {
			numberEvent(d8, klik8);
		});

		d9.setOnMouseClicked((MouseEvent klik9) -> {
			numberEvent(d9, klik9);
		});

		dzar.setOnMouseClicked((MouseEvent klikz) -> {
			numberEvent(dzar, klikz);
		});

		/* Unos operacija sa dugmica */
		dsab.setOnMouseClicked((MouseEvent ksab) -> {
			numberEvent(dsab, ksab);
		});

		dod.setOnMouseClicked((MouseEvent kod) -> {
			numberEvent(dod, kod);
		});

		dmn.setOnMouseClicked((MouseEvent kmn) -> {
			numberEvent(dmn, kmn);
		});

		ddj.setOnMouseClicked((MouseEvent kdj) -> {
			numberEvent(ddj, kdj);
		});

		brackl.setOnMouseClicked((MouseEvent kbrl) -> {
			numberEvent(brackl, kbrl);
		});
		brackr.setOnMouseClicked((MouseEvent kbrr) -> {
			numberEvent(brackr, kbrr);
		});
		pow.setOnMouseClicked((MouseEvent kpow) -> {
			numberEvent(pow, kpow);
		});
		sqrt.setOnMouseClicked((MouseEvent ksqrt) -> {
			numberEvent(sqrt, ksqrt);
		});

		dn.setDisable(true);

		/* REZULTAT */

		drez.setOnMousePressed((MouseEvent kres) -> {
			resultEvent(kres);
			drez.setStyle("-fx-text-fill: white; -fx-background-color: forestgreen;");
		});

		drez.setOnMouseMoved((MouseEvent kover) -> {
			drez.setStyle("-fx-text-fill: white; -fx-background-color: forestgreen;");
		});

		drez.setOnMouseExited((MouseEvent prit) -> {
			drez.setStyle("-fx-text-fill: white; -fx-background-color: limegreen; -fx-border-width: 1;\n"
					+ "-fx-border-radius: 1; fx-border-color: black;");
		});

		dback.setOnMouseClicked((MouseEvent kback) -> {
			deleteOneNumber(kback);
		});

		ddel.setOnMouseClicked((MouseEvent kdel) -> {
			deleteRez(kdel);
		});

		expression.setOnKeyTyped((KeyEvent key) -> {
			// System.out.println(expression.textProperty());
			// System.out.println(expression.getSelection().getStart());
		});

		expression.selectionProperty().addListener((p0, p1, p2) -> {
			// System.out.println("u textdieldu " + p0.getValue().getStart());
			// System.out.println("u textdieldu " + p1.getStart() + " " + p1.getEnd());
			// System.out.println("u textdieldu " + p2.getStart() + " " + p2.getEnd());

			textPos = p0.getValue().getStart();
			p1.getEnd();
			if (textPos > 0) {
				textPos1 = textPos;
			}

		});
		/*
		 * expression.caretPositionProperty().addListener((ob, old1, new1) -> {
		 * System.out.println("old:" + old1 + "  new " + new1.intValue()); textPos4 =
		 * old1.intValue() + new1.intValue(); });
		 * 
		 * expression.textProperty().addListener((ov, stara, nova) -> { if
		 * (nova.isEmpty()) { dn.setDisable(true); } else { dn.setDisable(false); } });
		 */

	}

	private void deleteOneNumber(MouseEvent kback) {
		String reznew = expression.getText();
		expression.clear();

		expression.setText(reznew.length() > 0
				? expression.getText() + reznew.replace(reznew.substring(reznew.length() - 1, reznew.length()), "")
				: "");

		/*
		 * for (int k = 0; k < reznew.length() - 1; k++) {
		 * expression.setText(expression.getText() + reznew.charAt(k)); }
		 */
	}

	private void deleteRez(MouseEvent kdel) {
		textPos1 = 0;
		expression.clear();
		resultLabel.setText(null);
	}

	private void numberEvent(Button dugme, MouseEvent me) {
		// expression.setFocusTraversable(true);
		String lst = "";

		// System.out.println(textPos + " " + textPos1 + " " + textPos2);
		// System.out.println(expression.caretPositionProperty().getValue().intValue());

		switch (me.getButton()) {

		case PRIMARY:

			if (expression.getText().length() == 0 && textPos1 > 0) {
				// textPos1 = 0;
				expression.selectRange(0, 0);
			} else if (expression.getText().length() >= 0 && textPos1 == 0) {
				// expression.clear();
				expression.setText(dugme.getText() + expression.getText());
				expression.selectRange(expression.getText().length(), expression.getText().length());
			} else if (textPos1 > 0) {
				if (textPos1 == expression.getText().length()) {
					expression.setText(expression.getText() + dugme.getText());
					expression.selectRange(expression.getText().length(), expression.getText().length());
				} else {
					for (int l = 0; l < expression.getText().length(); l++) {
						if (l == textPos1) {
							lst += dugme.getText();
							expression.selectRange(l, l);
						}
						lst += expression.getText().substring(l, l + 1);
					}
					expression.setText(lst);
				}
			}
			break;
		case MIDDLE:
			break;
		}
	}

	private void resultEvent(MouseEvent me) {

		// String tempExpression = expression.getText();
		expression.setFocusTraversable(true);

		if (me.getButton() == MouseButton.PRIMARY ) {
			resultLabel.setText(Calculation());			
		} 
	}

	/**** OPERACIJE VISEG REDA x^y & sqrt ****/

	private void sqrtpowOperation(String operat, Map<Integer, String> operator, Map<Integer, Double> operands) {

		ArrayList<Integer> operatorList = new ArrayList<>(operator.keySet());
		int i;
		for (i = 0; i < operatorList.size(); i++) {

			if (i > 0 && operator.get(operatorList.get(i - 1)).equals(operat)) {
				String operation = operator.get(operatorList.get(i - 1));
				operandsModification(operatorList.get(i - 1), operation, operands);
				operatorsModification(operatorList, operatorList.get(i - 1), operation, operator);
				i = i - 1;

			} else if (operator.get(operatorList.get(i)).equals(operat)) {
				String operation = operator.get(operatorList.get(i));
				operandsModification(operatorList.get(i), operation, operands);
				operatorsModification(operatorList, operatorList.get(i), operation, operator);
			}

			if (i > 0 && (i == operator.size() - 1) && operator.get(operatorList.get(i)).equals(operat)) {
				i = i - 1;
			}
		}
	}

	/**** OPERACIJE VISEG REDA * & / ****/
	private void highOperations(Map<Integer, String> operator, Map<Integer, Double> operands) {

		ArrayList<Integer> operatorList = new ArrayList<>(operator.keySet());
		int i;
		for (i = 0; i < operatorList.size(); i++) {

			if (i > 0 && (operator.get(operatorList.get(i - 1)).equals("*")
					|| operator.get(operatorList.get(i - 1)).equals("×")
					|| operator.get(operatorList.get(i - 1)).equals("/"))) {

				String operation = operator.get(operatorList.get(i - 1));
				operandsModification(operatorList.get(i - 1), operation, operands);
				operatorsModification(operatorList, operatorList.get(i - 1), operation, operator);
				i = i - 1;

			} else if (operator.get(operatorList.get(i)).equals("*") || operator.get(operatorList.get(i)).equals("×")
					|| operator.get(operatorList.get(i)).equals("/")) {

				String operation = operator.get(operatorList.get(i));
				operandsModification(operatorList.get(i), operation, operands);
				operatorsModification(operatorList, operatorList.get(i), operation, operator);

			}

			if (i > 0 && i == operator.size() - 1
					&& (operator.get(operatorList.get(i)).equals("*") || operator.get(operatorList.get(i)).equals("×")
							|| operator.get(operatorList.get(i)).equals("/"))) {
				i = i - 1;
			}

		}
	}

	/**** OPERACIJE NIZEG REDA '+' '-' ****/

	private void lowerOperations(Map<Integer, String> operator, Map<Integer, Double> operands) {

		ArrayList<Integer> operatorList = new ArrayList<>(operator.keySet());
		int i;
		for (i = 0; i < operatorList.size(); i++) {

			if (i > 0 && i < operator.size()) {
				i = i - 1;
			}

			if (operator.get(operatorList.get(i)).equals("+") || operator.get(operatorList.get(i)).equals("-")) {
				String operation = operator.get(operatorList.get(i));
				operandsModification(operatorList.get(i), operation, operands);
				operatorsModification(operatorList, operatorList.get(i), operation, operator);
			}

			if (operator.size() == 1) {
				String operation = operator.get(operatorList.get(i));
				operandsModification(operatorList.get(i), operation, operands);
				operatorsModification(operatorList, operatorList.get(i), operation, operator);
			}
		}
	}

	/**** MODIFIKACIJA MAPE SA OPERATORIMA ****/

	private void operatorsModification(List<Integer> list, Integer ind, String operation,
			Map<Integer, String> operator) {

		operator.remove(ind);

		Map<Integer, String> temp = new LinkedHashMap<>(operator);

		Iterator<Integer> operandIterator = (temp.keySet()).iterator();
		Integer iter;

		list.clear();

		while (operandIterator.hasNext()) {

			iter = operandIterator.next();
			if (iter < ind) {
				list.add(iter);
			}

			if (operation.equals("sqrt") && iter > ind && operator.containsKey(iter)) {
				operator.put(iter - 1, operator.get(iter));
				operator.remove(iter);
				list.add(iter - 1);
			} else if (iter > ind && operator.containsKey(iter)) {
				operator.put(iter - 2, operator.get(iter));
				operator.remove(iter);
				list.add(iter - 2);
			}
		}
		temp.clear();
	}

	/**** MODIFIKACIJA MAPE SA OPERATORIMA ****/

	private void operandsModification(Integer ind, String operation, Map<Integer, Double> operands) {

		switch (operation) {

		case "+":

			if (operands.containsKey(ind - 1) && operands.containsKey(ind + 1)) {
				operands.put(ind - 1, operands.get(ind - 1) + operands.get(ind + 1));
			}

			break;

		case "-":

			if (operands.containsKey(ind - 1) && operands.containsKey(ind + 1)) {
				operands.put(ind - 1, operands.get(ind - 1) - operands.get(ind + 1));
			}

			break;

		case "*":

			if (operands.containsKey(ind - 1) && operands.containsKey(ind + 1)) {
				operands.put(ind - 1, operands.get(ind - 1) * operands.get(ind + 1));
			}

			break;

		case "×":

			if (operands.containsKey(ind - 1) && operands.containsKey(ind + 1)) {
				operands.put(ind - 1, operands.get(ind - 1) * operands.get(ind + 1));
			}

			break;

		case "/":

			if (operands.containsKey(ind - 1) && operands.containsKey(ind + 1)) {
				operands.put(ind - 1, operands.get(ind - 1) / operands.get(ind + 1));
			}

			break;

		case "÷":

			if (operands.containsKey(ind - 1) && operands.containsKey(ind + 1)) {
				operands.put(ind - 1, operands.get(ind - 1) / operands.get(ind + 1));
			}

			break;

		case "^":

			if (operands.containsKey(ind - 1) && operands.containsKey(ind + 1)) {
				operands.put(ind - 1, Math.pow(operands.get(ind - 1), operands.get(ind + 1)));
			}

			break;

		case "sqrt":

			if (operands.containsKey(ind + 1)) {
				operands.put(ind, Math.sqrt(operands.get(ind + 1)));
			}

			break;
		}

		operands.remove(ind + 1);

		Map<Integer, Double> temp = new LinkedHashMap<>(operands);
		Iterator<Integer> operandIterator = temp.keySet().iterator();
		Integer iter;
		// System.out.println(operands);
		while (operandIterator.hasNext()) {

			if (operation.equals("sqrt")) {
				if ((iter = operandIterator.next()) > ind && operands.containsKey(iter)) {
					operands.put(iter - 1, operands.get(iter));
					operands.remove(iter);
				}
			} else {
				if ((iter = operandIterator.next()) > (ind - 1) && operands.containsKey(iter)) {
					operands.put(iter - 2, operands.get(iter));
					operands.remove(iter);
				}
			}
		}
		temp.clear();
	}

	/**** PODESAVANJE JEDNACINE ****/
	private String pushBlackFields(String str) {

		String returnStr = new String();

		for (Character ch : str.toCharArray()) {
			if (ch == '+' || ch == '-' || ch == '*' || ch == '×' || ch == '/' || ch == '÷' || ch == '^') {
				returnStr = returnStr + " " + ch + " ";
			} else if (ch == 's') {
				returnStr = returnStr + " " + ch;
			} else if (ch == 'q' || ch == 'r') {
				returnStr = returnStr + ch;
			} else if (ch == 't') {
				returnStr = returnStr + ch + " ";
			} else if (ch == '(') {
				returnStr = returnStr + ch + " ";
			} else if (ch == ')') {
				returnStr = returnStr + " " + ch;
			} else {
				returnStr += ch;
			}
		}
		return returnStr.trim();
	}

	public String Calculation() {

		if(expression.getText().length() == 0)
			return "";
		else if(expression.getText().length() == 1 && !Character.isDigit(expression.getText().charAt(0)))
			return "Wrong expression!";
		
		covertedExpression = pushBlackFields(expression.getText());

		String[] strArray = covertedExpression.split("\\s{1,}");

		String result = "";

		long sqrtCounter = 0;
		
		operators = new TreeMap<>();
		operands = new TreeMap<>();
		resultOperators = new TreeMap<>();
		resultOperands = new TreeMap<>();

		try {

			/**** < UCITAVANJE U MAPE > ****/
			int i, m = 0;
			for (i = 0; i < strArray.length; i++) {

				if (strArray[i].equals("(")) {

					int k = i + 1, l = 0;
					while (!strArray[k].equals(")")) {

						if (strArray[k].equals("+") || strArray[k].equals("-") || strArray[k].equals("*")
								|| strArray[k].equals("×") || strArray[k].equals("/") || strArray[k].equals("÷")
								|| strArray[k].equals("^") || strArray[k].equals("sqrt")) {
							operators.put(l, strArray[k]);
						} else {
							operands.put(l, Double.parseDouble(strArray[k]));
						}
						k++;
						l++;
						i = k;
					}

					System.out.println("Inside operators before: " + operators);
					System.out.println("Inside operands before: " + operands + "\n");
					
					sqrtCounter = operators.values().stream().filter(sqrt -> sqrt.equals("sqrt")).count();
					
					if(sqrtCounter > operands.size()) {
						return "Wrong expression!";
					}
					
					while (operators.containsValue("sqrt")) {
						sqrtpowOperation("sqrt", operators, operands);
					}

					System.out.println("Inside operators before: " + operators);
					System.out.println("Inside operands before: " + operands + "\n");

					if (operators.size() >= operands.size() && operators.size() > 1 && operands.size() > 1) {
						return "Wrong expression!";
					} else if (operators.size() >= operands.size() && operators.size() == 1
							&& operands.size() == 1 /* && !resultOperators.containsValue("sqrt") */) {
						return "Wrong expression!";
					} else if (operators.size() == 0 && operands.size() >= 2) {
						return "Wrong expression!";
					} else if (operators.size() < operands.size() || operators.size() == operands.size()) {

						sqrtpowOperation("^", operators, operands);
						highOperations(operators, operands);
						lowerOperations(operators, operands);

						System.out.println("Inside operators after: " + operators);
						System.out.println("Inside operands after: " + operands + "\n");

						if (operators.size() == 0 && operands.size() >= 2) {
							return "Wrong expression!";
						}

						resultOperands.put(m, operands.get(0));
						operands.clear();
						m++;
					}

				} else if (strArray[i].equals("+") || strArray[i].equals("-") || strArray[i].equals("*")
						|| strArray[i].equals("×") || strArray[i].equals("/") || strArray[i].equals("÷")
						|| strArray[i].equals("^") || strArray[i].equals("sqrt")) {
					resultOperators.put(m, strArray[i]);
					m++;
				} else {
					resultOperands.put(m, Double.parseDouble(strArray[i]));
					m++;
				}
			}

			System.out.println("Outside operators before: " + resultOperators);
			System.out.println("Outside operands before: " + resultOperands + "\n");
			
			sqrtCounter = resultOperators.values().stream().filter(sqrt -> sqrt.equals("sqrt")).count();
			
			if(sqrtCounter > resultOperands.size()) {
				return "Wrong expression!";
			}
			
			while (resultOperators.containsValue("sqrt")) {
				sqrtpowOperation("sqrt", resultOperators, resultOperands);
			}
			
			System.out.println("Outside operators before: " + resultOperators);
			System.out.println("Outside operands before: " + resultOperands + "\n");

			if (resultOperators.size() >= resultOperands.size() && (resultOperators.size() > 1
					|| resultOperands.size() > 1)) {
				return "Wrong expression!";
			} else if (resultOperators.size() >= resultOperands.size() && resultOperators.size() == 1
					&& resultOperands.size() == 1 ) {
				return "Wrong expression!";
			} else if (resultOperators.size() == 0 && resultOperands.size() >= 2) {
				return "Wrong expression!";
			} else if (resultOperators.size() < resultOperands.size() || (resultOperators.size() == resultOperands
					.size() )) {

				sqrtpowOperation("^", resultOperators, resultOperands);
				highOperations(resultOperators, resultOperands);
				lowerOperations(resultOperators, resultOperands);

				System.out.println("Outside operators after: " + resultOperators);
				System.out.println("Outside operands after: " + resultOperands + "\n");

				if (resultOperators.size() == 0 && resultOperands.size() >= 2) {
					return "Wrong expression!";
				}
				
				result = resultOperands.get(0).toString();
				resultOperands.clear();
			}

		} catch (Exception evaluateException) {
			// System.out.println("Wrong expression! ");
			return "Wrong expression!";
		}

		return result;

	}

}
