//
//  ComposeView.swift
//  iosContactsMP
//
//  Created by Felix Hennerich on 25.08.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }
}
