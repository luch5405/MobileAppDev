//
//  ViewController.swift
//  Lab3
//
//  Created by Lucca Chiappetta on 9/22/21.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBOutlet weak var darkModeLabel: UILabel!
    @IBOutlet weak var capsLabel: UILabel!
    @IBOutlet weak var animalPicture: UIImageView!
    @IBOutlet weak var sliderText: UILabel!
    @IBOutlet weak var animalText: UILabel!
    @IBOutlet weak var controlOutlet: UISegmentedControl!
    @IBOutlet weak var sliderOutlet: UISlider!
    var isCaps = false
    
    
    @IBAction func slider(_ sender: UISlider) {
        let fontSize = sender.value
        sliderText.text = String(format: "%.0f", fontSize)
        let fontSizeCGFloat = CGFloat(fontSize)
        animalText.font = UIFont.systemFont(ofSize: fontSizeCGFloat)
    }
    
    @IBAction func color(_ sender: UISwitch) {
        if sender.isOn{
            view.backgroundColor = .black
            animalText.textColor = .white
            capsLabel.textColor = .white
            darkModeLabel.textColor = .white
            controlOutlet.backgroundColor = .white
            sliderText.textColor = .white
            sliderOutlet.minimumTrackTintColor = .white
        }
        else{
            view.backgroundColor = .white
            animalText.textColor = .black
            capsLabel.textColor = .black
            darkModeLabel.textColor = .black
            controlOutlet.backgroundColor = .clear
            sliderText.textColor = .black
            sliderOutlet.minimumTrackTintColor = .black
        }
    }
    @IBAction func control(_ sender: UISegmentedControl) {
        if controlOutlet.selectedSegmentIndex == 0{
            animalText.text = "dog"
            animalPicture.image = UIImage(named: "dog")
        }
        if controlOutlet.selectedSegmentIndex == 1{
            animalText.text = "cat"
            animalPicture.image = UIImage(named: "cat")
        }
        if controlOutlet.selectedSegmentIndex == 2{
            animalText.text = "bird"
            animalPicture.image = UIImage(named: "bird")
        }
        
        if(isCaps == true){
            animalText.text = animalText.text?.uppercased()
        }
    }
    
    @IBAction func caps(_ sender: UISwitch) {
        if sender.isOn{
            animalText.text = animalText.text?.uppercased()
            isCaps = true
        }
        else{
            animalText.text = animalText.text?.lowercased()
            isCaps = false
        }
    }
}

