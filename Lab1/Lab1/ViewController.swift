//
//  ViewController.swift
//  Lab1
//
//  Created by Lucca Chiappetta on 9/7/21.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    var count = 0
    @IBOutlet weak var artImage: UIImageView!
    
    @IBOutlet weak var label: UILabel!
    

    @IBAction func next(_ sender: UIButton)
    {
        if count != 2{
            count += 1
        }
        
        if count == 0{
            artImage.image = UIImage(named: "cast")
            label.text = "Cast"
        }
        if count == 1{
            artImage.image = UIImage(named: "knot")
            label.text = "Knot"
        }
        else if count == 2{
            artImage.image = UIImage(named: "fly")
            label.text = "Fly"
        }
    }
    @IBAction func prev(_ sender: UIButton) {
        if count != 0 {
            count -= 1
        }
        
        if count == 0{
            artImage.image = UIImage(named: "cast")
            label.text = "Cast"
        }
        if count == 1{
            artImage.image = UIImage(named: "knot")
            label.text = "Knot"
        }
        else if count == 2{
            artImage.image = UIImage(named: "fly")
            label.text = "Fly"
        }
    }
}
