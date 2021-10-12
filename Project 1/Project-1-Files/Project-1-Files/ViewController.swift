//
//  ViewController.swift
//  Project-1-Files
//
//  Created by Lucca Chiappetta on 10/5/21.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var bg: UIView!
    let circle = CAShapeLayer()
    let circle1 = CAShapeLayer()
    let circle2 = CAShapeLayer()
    let circle3 = CAShapeLayer()
    let circle4 = CAShapeLayer()
    let circle5 = CAShapeLayer()
    let circle6 = CAShapeLayer()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let notificationCenter = NotificationCenter.default
        notificationCenter.addObserver(self, selector: #selector(appMovedToBackground), name: UIApplication.willResignActiveNotification, object: nil)
        
        //Gradient
        //code help from https://techion.com.au/blog/2018/11/14/creating-gradient-backgrounds-in-swift
        let gradientLayer = CAGradientLayer()
        gradientLayer.frame = view.bounds
        gradientLayer.colors = [
            CGColor.init(red: 29/255, green: 57/255, blue: 115/255, alpha: 1),
            CGColor.init(red: 0.184, green: 0.435, blue: 0.588, alpha: 1)
        ]
        gradientLayer.startPoint = CGPoint(x: 0.8, y: 0)
        gradientLayer.endPoint = CGPoint(x: 0.5, y: 1)
        gradientLayer.zPosition = -2
        bg.layer.addSublayer(gradientLayer)
        
        
        //cicles
        circle.path = UIBezierPath(arcCenter: playButtonOut.center, radius: 150, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: true).cgPath
        circle.lineWidth = 3
        circle.strokeColor = CGColor.init(red: 1, green: 1, blue: 1, alpha: 0.5)
        circle.fillColor = nil
        view.layer.addSublayer(circle)
        circle.zPosition = -1
        
        circle1.path = UIBezierPath(arcCenter: playButtonOut.center, radius: 200, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: true).cgPath
        circle1.lineWidth = 3
        circle1.strokeColor = CGColor.init(red: 1, green: 1, blue: 1, alpha: 0.5)
        circle1.fillColor = nil
        view.layer.addSublayer(circle1)
        circle1.zPosition = -1
        
        circle2.path = UIBezierPath(arcCenter: playButtonOut.center, radius: 250, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: true).cgPath
        circle2.lineWidth = 3
        circle2.strokeColor = CGColor.init(red: 1, green: 1, blue: 1, alpha: 0.5)
        circle2.fillColor = nil
        view.layer.addSublayer(circle2)
        circle2.zPosition = -1
        
        circle3.path = UIBezierPath(arcCenter: playButtonOut.center, radius: 300, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: true).cgPath
        circle3.lineWidth = 3
        circle3.strokeColor = CGColor.init(red: 1, green: 1, blue: 1, alpha: 0.5)
        circle3.fillColor = nil
        view.layer.addSublayer(circle3)
        circle3.zPosition = -1
        
        circle4.path = UIBezierPath(arcCenter: playButtonOut.center, radius: 450, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: true).cgPath
        circle4.lineWidth = 3
        circle4.strokeColor = CGColor.init(red: 1, green: 1, blue: 1, alpha: 0.5)
        circle4.fillColor = nil
        view.layer.addSublayer(circle4)
        circle4.zPosition = -1
        
        circle5.path = UIBezierPath(arcCenter: playButtonOut.center, radius: 600, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: true).cgPath
        circle5.lineWidth = 3
        circle5.strokeColor = CGColor.init(red: 1, green: 1, blue: 1, alpha: 0.5)
        circle5.fillColor = nil
        view.layer.addSublayer(circle5)
        circle5.zPosition = -1
        
        circle6.path = UIBezierPath(arcCenter: playButtonOut.center, radius: 800, startAngle: 0, endAngle: 2 * CGFloat.pi, clockwise: true).cgPath
        circle6.lineWidth = 3
        circle6.strokeColor = CGColor.init(red: 1, green: 1, blue: 1, alpha: 0.5)
        circle6.fillColor = nil
        view.layer.addSublayer(circle6)
        circle6.zPosition = -1
    }
    
//    func circlesIn(){
//        let animator = CAAnimation()
//    }
// couldnt get to work
    
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var songsLabel: UILabel!
    @IBOutlet weak var sessionsLabel: UILabel!
    @IBOutlet weak var playButtonOut: UIImageView!
    @IBOutlet weak var switchOut: UISwitch!
    @IBOutlet weak var pulseCirc: UIImageView!
    
    @IBOutlet weak var numSongs: UILabel!
    
    @IBOutlet weak var bottomStack: UIStackView!
    @IBOutlet weak var bottomScreen: UIImageView!
    
    
    @IBAction func switchAct(_ sender: Any) {
        if (switchOut.isOn){
            let time = secToMinToHr(seconds: user.calcTime())
            let finalTime = timeToStr(hours: time.0, mins: time.1, secs: time.2)
            
            timeLabel.text = finalTime
            songsLabel.text = String(user.calcSongs())
        }else{
            updateLabels()
        }
        
    }
    
    var user = User(username: "username")
    
    var timer:Timer = Timer()
    var count: Int = 0
    var songs: Int = 0
    var timerCounting: Bool = false
    
    let reachability = try! Reachability()
    
    //timer help and adaptation from https://www.youtube.com/watch?v=3TbdoVhgQmE
    @IBAction func mainButton(_ sender: UIButton) {
        if(timerCounting == true){ //stop timer
            
            timerCounting = false
            timer.invalidate()
            
            //add to arrays
            let time = secToMinToHr(seconds: count)
            let finalTime = timeToStr(hours: time.0, mins: time.1, secs: time.2)
            
            user.timeInt.append(count)
            user.time.append(finalTime)
            user.songs.append(songs)
            user.sessions += 1
            
            //update labels
            updateLabels()
            
            //reset timer and songs
            count = 0
            songs = 0
            //switch image
            playButtonOut.image = UIImage(named: "play")
            //stop animation
            //couldnt figure out
            
            //move bottom
            let bottomAni = UIViewPropertyAnimator(duration: 1, curve: .easeInOut, animations:{self.bottomScreen.transform = CGAffineTransform(translationX: 0, y: 0)})
            bottomAni.startAnimation()
                                                  
            let bottomStackAni = UIViewPropertyAnimator(duration: 1, curve: .easeInOut, animations:{self.bottomStack.transform = CGAffineTransform(translationX: 0, y: 0)})
            bottomStackAni.startAnimation()
            DispatchQueue.main.asyncAfter(deadline: .now() + 1){ //delay 1 sec
                self.numSongs.alpha = 0
            }
        }
        else{ //start timer
            
            //help from https://www.youtube.com/watch?v=tPPRmB_EZkY
            reachability.whenReachable = { [self] reachability in
                if (reachability.connection != .unavailable) {
                    //alert
                    let alert = UIAlertController(title: "Airplane Mode", message: "We reccomend you use airplane mode to reduce your usage. Limitting your connection will reduce notifications", preferredStyle: UIAlertController.Style.alert)
                    let okAlert = UIAlertAction(title: "Ok", style: UIAlertAction.Style.default)
                    
                    alert.addAction(okAlert)
                    present(alert, animated: true, completion: nil)
                }
            }
            timerCounting = true
            //switch image
            playButtonOut.image = UIImage(named: "pause")
            scaleImage()
            //start timer
            timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(timerCounter), userInfo: nil, repeats: true)
            //stop animation
            //couldnt get to work
            
            //move bottom
            numSongs.alpha = 1
            let bottomAni = UIViewPropertyAnimator(duration: 1, curve: .easeInOut, animations:{self.bottomScreen.transform = CGAffineTransform(translationX: 0, y: 500)})
            bottomAni.startAnimation()
                                                  
            let bottomStackAni = UIViewPropertyAnimator(duration: 1, curve: .easeInOut, animations:{self.bottomStack.transform = CGAffineTransform(translationX: 0, y: 500)})
            bottomStackAni.startAnimation()
        }
    }
    
    @objc func timerCounter() -> Void{
        count += 1
        if (count % 210 == 0){ //after 3:30 (1 song)
            songs += 1
        }
        numSongs.text = String(songs)
        scaleImage()
        
        let pulseOut = UIViewPropertyAnimator(duration: 0.5, curve: .easeInOut, animations:{self.pulseCirc.transform = CGAffineTransform(scaleX: 1.8, y: 1.8)}
        )
        pulseOut.startAnimation()
        
        let pulseIn = UIViewPropertyAnimator(duration: 0.5, curve: .easeInOut, animations:{self.pulseCirc.transform = CGAffineTransform(scaleX: 1, y: 1)}
        )
        pulseIn.startAnimation()
    }
    
    func secToMinToHr(seconds: Int) -> (Int, Int, Int){
        return ((seconds / 3600), ((seconds % 3600) / 60), ((seconds % 3600) % 60))
    }
    
    func timeToStr(hours: Int, mins: Int, secs: Int) -> String{
        var timeStr = ""
        timeStr += String(format: "%02d", hours)
        timeStr += " : "
        timeStr += String(format: "%02d", mins)
        timeStr += " : "
        timeStr += String(format: "%02d", secs)
        return timeStr
    }
    
    func updateLabels(){
        if (user.time.isEmpty){
            timeLabel.text = "00 : 00 : 00"
        }else{
            timeLabel.text = user.time.last!
        }
        
        if (user.songs.isEmpty){
            songsLabel.text = "0"
        }else{
            songsLabel.text = String(user.songs.last!)
        }

        sessionsLabel.text = String(user.sessions)
        
    }
    
    func scaleImage(){
        
        //Animation
        let animator = UIViewPropertyAnimator(duration: 3, curve: .easeInOut, animations:{self.playButtonOut.transform = CGAffineTransform(scaleX: 1.8, y: 1.8)}
        )
        animator.startAnimation()
        
        let animator2 = UIViewPropertyAnimator(duration: 3, curve: .easeInOut, animations:{self.playButtonOut.transform = CGAffineTransform(scaleX: 1, y: 1)}
        )
        animator2.startAnimation()
    }
    
    @objc func appMovedToBackground() -> Bool{
        print("App moved to background!")
        return true
    }
    
}

class User{
    var name: String
    var time: [String] = []
    var timeInt: [Int] = []
    var songs: [Int] = []
    var sessions: Int = 0
    
    
    
    init(username uname : String) {
        name = uname
    }
    
    func calcTime() -> Int{
        var totalTime: Int = 0
        for item in timeInt{
            totalTime += item
        }
        return totalTime
        
    }

    func calcSongs() -> Int{
        var totalSongs : Int = 0
        for item in songs{
            totalSongs += item
        }
        return totalSongs
    }
}


