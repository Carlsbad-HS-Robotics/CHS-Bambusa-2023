package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@Config
public class Wheel {

    static double conversionFactor = (360 / 3.3);

    private DcMotor speedMotor;
    private CRServo angleMotor;

    private AnalogInput encoder;

    private PID pid = new PID(PID.Type.move);

    private double lastTargetAngle;

    private double lastAngle = 0;

    private double totalAngle = 0;
    public static double kp = .1;
    public static double ki = 0;
    public static double kd = 0;

    public double calculateError(double current, double target, OpMode teleop) {

        double error1;
        double error2;

        if (current > target) {
            error2 = (target + 360) - current;
        } else {
            error2 = (target - 360) - current;
        }

        error1 = target - current;

        teleop.telemetry.addData("Target", target);
        teleop.telemetry.addData("Current", current);

        return Math.abs(error1) > Math.abs(error2) ? error2 : error1;
    }

    public Wheel(DcMotor speed, CRServo angle, AnalogInput enc) {
        speedMotor = speed;
        angleMotor = angle;
        encoder = enc;

        pid.setConstantsMove(kp,ki,kd);
    }

    public void drive(double speed, double targetangle, OpMode teleop) {
        if (targetangle != lastTargetAngle) {
            pid.reset();
            lastTargetAngle = targetangle;
        }

        double currentAngle = encoder.getVoltage() * conversionFactor;

        double error = calculateError(currentAngle, targetangle, teleop);

        totalAngle += calculateError(lastAngle, currentAngle, teleop);

        teleop.telemetry.addData("Error", error);

        speedMotor.setPower (speed);

        pid.pid(error, totalAngle);

        angleMotor.setPower (pid.pidout/20);

        lastAngle = currentAngle;

        teleop.telemetry.addData("pid out", pid.pidout);

//        double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
//
//        if (setpoint < 0) {
//            setpoint = MAX_VOLTS + setpoint;
//        }
//
//        if (setpoint > MAX_VOLTS) {
//            setpoint = setpoint - MAX_VOLTS;
//        }
//
//        pidController.setSetpoint (setpoint)
    }


}
