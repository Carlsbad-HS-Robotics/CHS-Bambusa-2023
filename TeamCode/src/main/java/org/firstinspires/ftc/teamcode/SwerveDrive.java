package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.util.RobotLog;

public class SwerveDrive {

    public final double L = .2171;
    public final double W = .2171;

    private Wheel backRight;
    private Wheel backLeft;
    private Wheel frontRight;
    private Wheel frontLeft;

    public SwerveDrive(Wheel backRight, Wheel backLeft, Wheel frontRight, Wheel frontLeft) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
    }

    public void drive (double x1, double y1, double x2, OpMode teleop) {
        double r = Math.sqrt ((L * L) + (W * W));
        y1 *= -1;

        double a = x1 - x2 * (L / r);
        double b = x1 + x2 * (L / r);
        double c = y1 - x2 * (L / r);
        double d = y1 + x2 * (L / r);

        double backRightSpeed = Math.sqrt ((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));

        double backRightAngle = Math.atan2 (a, d) * 180 / Math.PI;
        double backLeftAngle = Math.atan2 (a, c) * 180 / Math.PI;
        double frontRightAngle = Math.atan2 (b, d) * 180 / Math.PI;
        double frontLeftAngle = Math.atan2 (b, c) * 180 / Math.PI;

        //RobotLog.d("ANGLE0: back right angle: " + backRightAngle);


        teleop.telemetry.addData("back right angle", backRightAngle);
//        teleop.telemetry.addData("front right angle", frontRightAngle);
//        teleop.telemetry.addData("back left angle", backLeftAngle);
//        teleop.telemetry.addData("front left angle", frontLeftAngle);

        backRight.drive (backRightSpeed, backRightAngle, teleop);
//        backLeft.drive (backLeftSpeed, backLeftAngle, teleop);
//        frontRight.drive (frontRightSpeed, frontRightAngle, teleop);
//        frontLeft.drive (frontLeftSpeed, frontLeftAngle, teleop);
    }
}
