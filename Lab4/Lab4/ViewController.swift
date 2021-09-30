//
//  ViewController.swift
//  Lab4
//
//  Created by Lucca Chiappetta on 9/29/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    override func viewDidLoad() {
        buyAmount.delegate = self
        sellAmount.delegate = self
        super.viewDidLoad()
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard))
        view.addGestureRecognizer(tap)
    }
    
    @objc func dismissKeyboard(){
            view.endEditing(true)
        }
    
    
    @IBOutlet weak var buyAmount: UITextField!
    @IBOutlet weak var sellAmount: UITextField!
    @IBOutlet weak var stockLabel: UILabel!
    @IBOutlet weak var stockStepper: UIStepper!
    @IBOutlet weak var profit: UILabel!
    @IBOutlet weak var profitLabel: UILabel!
    
    @IBAction func stepperFunc(_ sender: UIStepper) {
        if stockStepper.value == 1{
            stockLabel.text = "1 Stock"
        }
        else {
            stockLabel.text = String(format: "%.0f", stockStepper.value) + " Stocks"
        }
        updateStocks()
    }
    
    func updateStocks() {
        var sell: Float
        var buy: Float
        
        if buyAmount.text!.isEmpty{
            buy = 0.0;
        } else{
            buy = Float(buyAmount.text!)!
        }
        
        if sellAmount.text!.isEmpty{
            sell = 0.0;
        } else{
            sell = Float(sellAmount.text!)!
        }
        
        let numStocks = Float(stockStepper.value)
        let results = (sell * numStocks) - (buy * numStocks)
        print(sell)
        print(buy)
        print(results)
        
        if(numStocks == 0){
            let alert = UIAlertController(title: "Warning", message: "the number of stocks must be greater than 0", preferredStyle: UIAlertController.Style.alert)
            let cancelAlert=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
            alert.addAction(cancelAlert)
            
            let okAlert = UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: {
                action in
                self.stockStepper.value = 1
                self.stockLabel.text? = "1 Stock"
                self.updateStocks()
            })
            
            alert.addAction(okAlert)
            present(alert, animated: true, completion: nil)
            
        }
        let format = NumberFormatter()
        format.numberStyle = NumberFormatter.Style.currency
        
        
        if (results >= 0){
            profitLabel.text = "Profit"
            profit.textColor = .green
            profit.text = format.string(from: NSNumber(value: results))
        }
        
        else{
            profitLabel.text = "Deficit"
            profit.textColor = .red
            profit.text = format.string(from: NSNumber(value: results))
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateStocks()
    }
    
}

